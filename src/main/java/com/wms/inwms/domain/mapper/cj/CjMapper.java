package com.wms.inwms.domain.mapper.cj;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CjMapper {
    int send(CJDeliveryDto cjDeliveryDto);
    List<Map<String, String>> searchTracking(Map<String, List<String>> numbers);
}
