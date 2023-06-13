package com.wms.inwms.domain.inbound;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.inbound.dto.InboundSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : InboundService
 * author         : akfur
 * date           : 2023-06-12
 */
@Service
public class InboundService extends BaseService<InboundEntity, Long> {

    private final InboundRepository repository;
    public InboundService(InboundRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<InboundResultDto.InboundSaveResultDto> saveInboundMapping(List<InboundSaveDto> saveDto) {

        return null;
    }



}
