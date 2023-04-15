package com.wms.inwms.domain.cj;


import com.wms.inwms.domain.base.BaseRepo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CJRepository extends BaseRepo<CJDto, Long> {
    List<CJDto> findAll();
}
