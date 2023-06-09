package com.wms.inwms.domain.returnOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.mapper.cj.CJDeliveryDto;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderSaveDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnResponseDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnSearchDto;
import com.wms.inwms.util.customException.CustomRunException;
import com.wms.inwms.util.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReturnService extends BaseService<ReturnEntity, Long> {

    private final ReturnRepository returnRepository;
    private final ObjectMapper mapper
    private final CjMapper cjMapper;

    public ReturnService(ReturnRepository returnRepository, CjMapper cjMapper, ObjectMapper mapper) {
        super(returnRepository);
        this.returnRepository = returnRepository;
        this.cjMapper = cjMapper;
        this.mapper = mapper;
    }

    public List<ReturnEntity> findAll() {
        return this.returnRepository.findAll();
    }

    @Transactional
    public List<ReturnOrderSaveDto> saveAll(List<ReturnOrderSaveDto> returnDataList) {
        try {
            List<ReturnEntity> returnEntities = returnDataList.stream().map(e -> mapper.convertValue(e, ReturnEntity.class)).collect(Collectors.toList());
            return this.returnRepository.saveAll(returnEntities);
        } catch (Exception e) {
            log.error("Save Exception", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public Page<ReturnEntity> find() {
        Pageable pageable = PageRequest.of(10, 10);

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<ReturnEntity> returnData = this.select().from(qReturn)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
        JPAQuery<Long> count = query.select(qReturn.count()).from(qReturn);

        return PageableExecutionUtils.getPage(returnData, pageable, count::fetchOne);
    }

    /**
     * ==============================================
     * <p> 일자 범위로 반품정보 검색
     * ==============================================
     * user : akfur
     * date : 2023-05-03
     *
     * @param pageable
     * @param returnSearchDto
     * @return Page<ReturnResponseDto>
     *
     * 페이징 리스트로 반환하며 ReturnEntity -> ReturnResponseDto 변환
     */
    @Transactional
    public Page<ReturnResponseDto> searchReturnData(Pageable pageable, @NotNull ReturnSearchDto returnSearchDto) {
        Instant startDate = returnSearchDto.getStartDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant endDate = returnSearchDto.getEndDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        Page<ReturnEntity> page = returnRepository.findByCreatedBetweenAndOrderNumContaining(
                startDate,
                endDate,
                returnSearchDto.getOrderNum(),
                pageable
        );
        return page.map(order -> Mapper.mapper(order, ReturnResponseDto.class));
    }

    /**
     * ==============================================
     * <p> CJ반품 신고(뷰테이블)
     * ==============================================
     * user : akfur
     * date : 2023-04-29
     *
     * @return List<ReturnEntity>
     * <p>
     * 전달받은 매개변수로 CJ 반품 데이터 리스트 생성 후 반품 신고, 신고완료 후 반품 신고상태(report_status) Y로 변경
     * CJDB는 뷰테이블만 제공하여 단일건으로만 insert가능
     */
    public List<ReturnEntity> shippingReportCJ(List<ReturnOrderDto> returnOrderDtoList) {
        try {
            List<CJDeliveryDto> deliveryCJData = createCJDeliveryList(returnOrderDtoList);

            /* CJDB는 뷰테이블만 제공하여 단일건으로 신고만 가능 일괄 신고 불가능 */
            List<String> successData = saveReturnCJDeli(deliveryCJData);

            return saveAll(reportSuccessDataFind(successData));
        } catch (DuplicateKeyException e) {
            /* 추후 유니크 관련 중복 에러처리 작성 */
            log.error("DuplicateKeyException Error", e.getMessage(), e);
            throw new CustomRunException("");
        }
    }

    /**
     * ==============================================
     * <p> CJ 반품신고 플래그 값 변경
     * ==============================================
     * user : akfur
     * date : 2023-05-03
     *
     * @param successData
     * @return List<ReturnEntity>
     *
     * 성공한 송장번호 조회 후 반품오더 report_status를 Y(반품신고 성공) 변경
     */
    private List<ReturnEntity> reportSuccessDataFind(List<String> successData) {
        return select().from(qReturn).where(qReturn.originNumber.in(successData)).fetch()
                .stream().peek(e -> e.setReportStatus("Y")).collect(Collectors.toList());
    }

    /**
     * ==============================================
     * <p> CJ반품 신고
     * ==============================================
     * user : akfur
     * date : 2023-05-03
     *
     * @param deliveryCJData
     * @return List<String>
     *
     * 반품신고 후 DB저장된 송장번호 리스트 반환
     */
    private List<String> saveReturnCJDeli(List<CJDeliveryDto> deliveryCJData) {
        List<String> successOriginNum = new ArrayList<>();
        for (CJDeliveryDto deliveryCJDatum : deliveryCJData) {
            if (cjMapper.send(deliveryCJDatum) > 0) ;
            successOriginNum.add(deliveryCJDatum.getOriInvcNo());
        }
        return successOriginNum;
    }

    /**
     * ==============================================
     * <p> CJ반품 데이터 생성
     * ==============================================
     * user : akfur
     * date : 2023-05-03
     *
     * @param returnOrderDtoList
     * @return List<CJDeliveryDto>
     *
     * CJ반품 데이터 생성 후 List반환(데이터는 빌드로 생성)
     */
    private List<CJDeliveryDto> createCJDeliveryList(List<ReturnOrderDto> returnOrderDtoList) {
        List<CJDeliveryDto> cjDeliveryDtoList = new ArrayList<>();

        for (ReturnOrderDto returnOrderDto : returnOrderDtoList) {
            String[] telNum = detailTelNum(returnOrderDto);
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));

            CJDeliveryDto cjDeliveryDto =
                    CJDeliveryDto.builder()
                            .custUseNo("TESTORDER8").oriOrdNo("testorder8")
                            .mpckKey(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")) + "_30244464" + returnOrderDto.getOriginNumber())
                            .sendrNm(returnOrderDto.getName())
                            .sendrTelNo1(telNum[0])
                            .sendrTelNo2(telNum[1])
                            .sendrTelNo3(telNum[2])
                            .sendrZipNo(returnOrderDto.getZipNo())
                            .sendrAddr(returnOrderDto.getAddr())
                            .sendrDetailAddr(returnOrderDto.getAddr())
                            .gdsNm(returnOrderDto.getGoodsName())
                            .gdsQty(returnOrderDto.getQty())
                            .oriInvcNo(returnOrderDto.getOriginNumber()).build();

            cjDeliveryDtoList.add(cjDeliveryDto);
        }
        return cjDeliveryDtoList;
    }

    /**
     * ==============================================
     * <p> CJ반품 송하인, 수취인 번호 분리
     * ==============================================
     * user : akfur
     * date : 2023-05-03
     *
     * @param returnOrderDto
     * @return String[]
     *
     * 송하인 또는 수취인 번호를 3개로 나눈다, 11자리(핸드펀번호) 9자리(일반번호) 외에는 미처리
     */
    private String[] detailTelNum(ReturnOrderDto returnOrderDto) {
        String[] detailTelNum = new String[3];
        String telNum = returnOrderDto.getTelNum().replace("-", "");

        /* 핸드폰 번호 11자리 일반 전화번호 9자리 */
        if (telNum.length() == 11) {
            detailTelNum[0] = telNum.substring(0, 3);
            detailTelNum[1] = telNum.substring(3, 7);
            detailTelNum[2] = telNum.substring(7, 11);
        } else if (telNum.length() == 9) {
            detailTelNum[0] = telNum.substring(0, 2);
            detailTelNum[1] = telNum.substring(2, 5);
            detailTelNum[2] = telNum.substring(4, 8);
        }

//                gpt코드..
//                final int CELL_NUM_LENGTH = 11;
//                final int TEL_NUM_LENGTH = 9;
//                int[] separator = telNum.length() == CELL_NUM_LENGTH ? new int[]{3, 7} : new int[]{2, 5};
//                int idx = 0;
//
//                for (int i = 0; i < separator.length; i++) {
//                    detailTelNum[i] = telNum.substring(idx, separator[i]);
//                    idx = separator[i];
//                }
//                detailTelNum[separator.length] = telNum.substring(idx);


//                IndexOutOfBoundsException
//                StringIndexOutOfBoundsException
//
//                CJDeliveryDto.builder()
//                        .sendrNm(returnOrderDto.getName())
//                        .sendrTelNo1(returnOrderDto.getTelNum())
        return detailTelNum;
    }
}

