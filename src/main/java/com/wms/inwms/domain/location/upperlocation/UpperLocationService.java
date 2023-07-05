package com.wms.inwms.domain.location.upperlocation;

import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpperLocationService extends BaseService<UpperLocation, Long> {

    private final UpperLocationRepository repository;
    public UpperLocationService(UpperLocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<UpperLocation> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<UpperLocation> findByName(String name) {
        return repository.findByUpLocationName(name);
    }
}
