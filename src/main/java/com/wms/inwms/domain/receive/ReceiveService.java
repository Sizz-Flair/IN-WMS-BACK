package com.wms.inwms.domain.receive;

import antlr.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveService extends BaseService<Receive, Long> {

    private ReceiveRepository repository;

    public ReceiveService(ReceiveRepository receiveRepository) {
        super(receiveRepository);
        this.repository = receiveRepository;
    }

    @Transactional
    public List<Receive> findByCreatedBetween() throws NullPointerException {
        LocalDateTime localDateTime = LocalDateTime.now();

        Instant endDate = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant startDate = localDateTime.minusDays(10).atZone(ZoneId.systemDefault()).toInstant();

        return repository.findByCreatedBetweenOrderByIdDesc(startDate, endDate).get();
    }

    public List<Receive>searchReceivingData(Instant startDate, Instant endDate, String reNumber) {
        BooleanBuilder builder = searchCondition(startDate, endDate, reNumber);
        return this.select().from(QReceive.receive).where(builder).fetch();
    }

    private BooleanBuilder searchCondition(Instant startDate, Instant endDate, String reNumber) {
        BooleanBuilder builder = new BooleanBuilder();
        if(startDate != null && endDate != null)
            builder.and(QReceive.receive.created.between(startDate, endDate));

        if(reNumber.isEmpty() && reNumber != null)
            builder.and(QReceive.receive.receiveNumber.eq(reNumber));

        return builder;
    }
}
