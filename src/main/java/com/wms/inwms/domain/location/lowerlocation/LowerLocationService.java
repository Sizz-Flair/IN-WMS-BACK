package com.wms.inwms.domain.location.lowerlocation;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LowerLocationService extends BaseService<LowerLocation, Long> {

    private final LowerLocationRepository repository;
    public LowerLocationService(LowerLocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<LowerLocation> findByName(String name) {
        return repository.findByLowLocationName(name);
    }
}
