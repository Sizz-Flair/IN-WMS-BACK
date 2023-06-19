package com.wms.inwms.domain.location.lowerlocation;

import com.wms.inwms.domain.base.BaseRepo;

import java.util.Optional;

public interface LowerLocationRepository extends BaseRepo<LowerLocation, Long> {
    Optional<LowerLocation> findByLowLocationName(String name);
}
