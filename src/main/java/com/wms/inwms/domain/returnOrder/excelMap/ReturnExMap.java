package com.wms.inwms.domain.returnOrder.excelMap;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import static java.util.Map.entry;

/**
 * packageName    : com.wms.inwms.domain.returnOrder.dto
 * fileName       : ReturnOrderExDto
 * author         : akfur
 * date           : 2023-05-03
 * <p>
 * ## 반품오더 엑셀 DTO
 */
@Getter
@Setter
public class ReturnExMap {

    public static Map<Integer, Map<String, String>> ReturnOrderExcel = Map.ofEntries(
            entry(0,Map.of("column","dateArrival","title","입항일자")),
            entry(1,Map.of("column","orderAccount","title","오더거래처")),
            entry(2,Map.of("column","agency","title","대리점")),
            entry(3,Map.of("column","deliveryCom","title","택배사")),
            entry(4,Map.of("column","number","title","송장번호")),
            entry(5,Map.of("column","originNumber","title","원송장번호")),
            entry(6,Map.of("column","qty","title","수량")),
            entry(7,Map.of("column","weight","title","중량")),
            entry(8,Map.of("column","price","title","물품가액")),
            entry(9,Map.of("column","goodsName","title","품명")),
            entry(10,Map.of("column","tel","title","전화번호")),
            entry(11,Map.of("column","addr","title","주소")),
            entry(12,Map.of("column","addrDetail","title","상세주소")),
            entry(13,Map.of("column","zipNo","title","우편번호")),
            entry(14,Map.of("column","shipper","title","보내는사람"))
    );

    private static Map<String, String> titleMap = Map.ofEntries(
            entry("no", "no"),
            entry("dateArrival", "입항일자"),
            entry("orderAccount", "오더거래처"),
            entry("agency", "대리점"),
            entry("deliveryCom", "택배사"),
            entry("number", "송장번호"),
            entry("originNumber", "원송장번호"),
            entry("qty", "수량"),
            entry("weight", "중량"),
            entry("price", "물품가액"),
            entry("goodsName", "품명"),
            entry("tel", "전화번호"),
            entry("addr", "주소"),
            entry("addrDetail", "상세주소"),
            entry("zipNo", "우편번호"),
            entry("shipper", "보내는사람")
    );

    public static Map<String, String> getReturnOrderEx() {
        return titleMap;
    }
}
