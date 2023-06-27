//package com.wms.inwms.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//
///**
// * packageName    : com.wms.inwms.util
// * fileName       : InstantToLocalDate
// * author         : akfur
// * date           : 2023-06-19
// */
//@Slf4j
//public class InstantToLocalDate implements Converter<String, LocalDateTime> {
//    @Override
//    public LocalDateTime convert(String instant) {
//        log.info("test Instant = {}", instant);
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        return LocalDateTime.parse(instant, format);
//    }
//}
