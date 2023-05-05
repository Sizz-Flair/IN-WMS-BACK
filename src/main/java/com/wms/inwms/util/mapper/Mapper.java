package com.wms.inwms.util.mapper;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

/**
 * packageName    : com.wms.inwms.util.mapper
 * fileName       : Mapper
 * author         : akfur
 * date           : 2023-05-03
 *
 * -- 스프링 Bean 주입으로 변경은 고민 사항.
 */
@NoArgsConstructor
public class Mapper {
    private static final ModelMapper mapper = new ModelMapper();

    synchronized public static<S, D> D mapper(S source, Class<D> type) {
        return mapper.map(source, type);
    }
}