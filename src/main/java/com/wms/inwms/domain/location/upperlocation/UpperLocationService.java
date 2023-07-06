package com.wms.inwms.domain.location.upperlocation;

import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.inbound.InboundEntity;
import com.wms.inwms.domain.location.upperlocation.dto.UpperLocationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UpperLocationService extends BaseService<UpperLocation, Long> {

    private final UpperLocationRepository repository;
    public UpperLocationService(UpperLocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<UpperLocation> findByName(String name) {
        return repository.findByUpLocationName(name);
    }

    /**
     * ==============================================
     * <p> 재고내역 모두 조회
     * ==============================================
     * user : akfur
     * date : 2023-07-06
     *
     * @return List<UpperLocationDto.UpperLocationResultDto>
     */
    public List<UpperLocationDto.UpperLocationResultDto> getStorage() {
        List<UpperLocation> data = this.select().from(qUpperLocation).fetch();
        return data.stream().map(e -> e.convertUpperDto()).collect(Collectors.toList());
    }
}
