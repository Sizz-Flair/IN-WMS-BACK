package com.wms.inwms.domain.returnOrder;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.mapper.cj.CJDeliveryDto;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnSearchDto;
import com.wms.inwms.util.customException.CustomException;
import com.wms.inwms.util.customException.CustomRunException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReturnService extends BaseService<ReturnEntity, Long> {

    private final ReturnRepository returnRepository;
    private final CjMapper cjMapper;

    public ReturnService(ReturnRepository returnRepository, CjMapper cjMapper) {
        super(returnRepository);
        this.returnRepository = returnRepository;
        this.cjMapper = cjMapper;
    }

    public List<ReturnEntity> findAll() {
        return this.returnRepository.findAll();
    }

    @Transactional
    public List<ReturnEntity> saveAll(List<ReturnEntity> returnDataList) {
        try {
            return this.returnRepository.saveAll(returnDataList);
        } catch (Exception e) {
            log.error("Save Exception", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public Page<ReturnEntity> find() {
        Pageable pageable = PageRequest.of(10, 10);

        JPAQueryFactory qq = new JPAQueryFactory(em);
        List<ReturnEntity> returnData = this.select().from(qReturn)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
        JPAQuery<Long> count = qq.select(qReturn.count()).from(qReturn);

        return PageableExecutionUtils.getPage(returnData, pageable, count::fetchOne);
    }

    @Transactional
    public Page<ReturnEntity> searchReturnData(Pageable pageable, @NotNull ReturnSearchDto returnSearchDto) {
        Instant startDate = returnSearchDto.getStartDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant endDate = returnSearchDto.getEndDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return returnRepository.findByCreatedBetweenAndOrderNumContaining(
                startDate,
                endDate,
                returnSearchDto.getOrderNum(),
                pageable
        );
    }

    /**
     * ==============================================
     * <p> CJ반품 신고(뷰테이블)
     * ==============================================
     * user : akfur
     * date : 2023-04-29
     *
     * @return List<ReturnEntity>
     */
    public List<ReturnEntity> shippingReportCJ(List<ReturnOrderDto> returnOrderDtoList) {
        List<CJDeliveryDto> deliveryCJData = new ArrayList<>();
        try {
            deliveryCJData = createCJDeliveryList(returnOrderDtoList);

            /* CJDB는 뷰테이블만 제공하여 단일건으로 신고만 가능 일괄 신고 불가능 */
            saveReturnCJDeli(deliveryCJData);

            iter
        } catch (IndexOutOfBoundsException e) {
            log.error("indexOutOfBound Error", e.getMessage(), e);
            throw new CustomRunException("");
        } finally {

        }
//        List<CJDto2> cjDto2List = new ArrayList<>();
//        LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
//
//        String mpckey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")) + "_30244464" + "567213753691";
//        String rcvrNm = "김현진";
//        String rcvrTelNo1 = "010";
//        String rcvrTelNo2 = "4024";
//        String rcvrTelNo3 = "3740";
//        String rcvrZipNo = "21400";
//        String rcvrAddr = "인천광역시 부평구 안남로 15번길 24";
//        String rcvrdetailAddr = "중앙하이츠 102동 1601호";
//        String ORI_INVC_NO = "567213753691";
//        String gdsNm = "슈퍼컴퓨터";
//        Long gdsQty = 1L;
//
//        CJDto2 cjDto2 = CJDto2.builder()
//                .custUseNo("TESTORDER-7") // 송장과 1:1 매칭
//                .oriOrdNo("TESTORDER-ORI-4")
//                .mpckKey(mpckey + ORI_INVC_NO)
//                .sendrNm(rcvrNm)
//                .sendrTelNo1(rcvrTelNo1)
//                .sendrTelNo2(rcvrTelNo2)
//                .sendrTelNo3(rcvrTelNo3)
//                .sendrZipNo(rcvrZipNo)
//                .sendrAddr(rcvrAddr)
//                .sendrDetailAddr(rcvrdetailAddr)
//                .gdsNm(gdsNm)
//                .gdsQty(gdsQty)
//                .remark1("remark").prtSt("02")
//                .build();

        //cjMapper.send(cjDto2);
        return null;
    }

    private void saveReturnCJDeli(List<CJDeliveryDto> deliveryCJData) {
        for (CJDeliveryDto deliveryCJDatum : deliveryCJData) {
            cjMapper.send(deliveryCJDatum);
        }
    }


    private List<CJDeliveryDto> createCJDeliveryList(List<ReturnOrderDto> returnOrderDtoList) {
        List<CJDeliveryDto> cjDeliveryDtoList = new ArrayList<>();

        for (ReturnOrderDto returnOrderDto : returnOrderDtoList) {
            String[] telNum = detailTelNum(returnOrderDto);

            CJDeliveryDto cjDeliveryDto =
                    CJDeliveryDto.builder()
                    .sendrNm(returnOrderDto.getName())
                    .sendrTelNo1(telNum[0])
                    .sendrTelNo2(telNum[1])
                    .sendrTelNo3(telNum[2])
                    .sendrZipNo(returnOrderDto.getZipNo())
                    .sendrAddr(returnOrderDto.getAddr())
                    .sendrDetailAddr(returnOrderDto.getAddr())
                    .oriInvcNo(returnOrderDto.getOriginNumber()).build();

            cjDeliveryDtoList.add(cjDeliveryDto);
        }
        return cjDeliveryDtoList;
    }

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

