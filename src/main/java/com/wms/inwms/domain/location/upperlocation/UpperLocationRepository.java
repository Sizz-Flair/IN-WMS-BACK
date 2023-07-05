package com.wms.inwms.domain.location.upperlocation;

import com.wms.inwms.domain.base.BaseRepo;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UpperLocationRepository extends BaseRepo<UpperLocation, Long> {
    Optional<UpperLocation> findById(Long id);
    Optional<UpperLocation> findByUpLocationName(String name);
}
