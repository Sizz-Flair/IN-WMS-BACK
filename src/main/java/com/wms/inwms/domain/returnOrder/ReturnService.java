package com.wms.inwms.domain.returnOrder;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.mapper.cj.CJDto2;
import com.wms.inwms.domain.mapper.cj.CjMapper;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDto;
import com.wms.inwms.domain.returnOrder.dto.ReturnSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
     * <p>
     * ==============================================
     * user : akfur
     * date : 2023-04-20
     *
     * @return List<ReturnEntity>
     */
    @Transactional
    public List<ReturnEntity> shippingReportCJ(List<ReturnOrderDto> returnOrderDtoList) {

        try {
            //returnOrderDtoList.
        } catch (Exception e) {

        }

        List<CJDto2> cjDto2List = new ArrayList<>();
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));

        String mpckey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")) + "_30244464" + "567213753691";
        String rcvrNm = "김현진";
        String rcvrTelNo1 = "010";
        String rcvrTelNo2 = "4024";
        String rcvrTelNo3 = "3740";
        String rcvrZipNo = "21400";
        String rcvrAddr = "인천광역시 부평구 안남로 15번길 24";
        String rcvrdetailAddr = "중앙하이츠 102동 1601호";
        String ORI_INVC_NO = "567213753691";
        String gdsNm = "슈퍼컴퓨터";
        Long gdsQty = 1L;

        CJDto2 cjDto2 = CJDto2.builder()
                .custUseNo("TESTORDER-7") // 송장과 1:1 매칭
                .oriOrdNo("TESTORDER-ORI-4")
                .mpckKey(mpckey + ORI_INVC_NO)
                .sendrNm(rcvrNm)
                .sendrTelNo1(rcvrTelNo1)
                .sendrTelNo2(rcvrTelNo2)
                .sendrTelNo3(rcvrTelNo3)
                .sendrZipNo(rcvrZipNo)
                .sendrAddr(rcvrAddr)
                .sendrDetailAddr(rcvrdetailAddr)
                .gdsNm(gdsNm)
                .gdsQty(gdsQty)
                .remark1("remark").prtSt("02")
                .build();

        cjMapper.send(cjDto2);
        return null;
    }
}

