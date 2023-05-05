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
 *
 * ## 반품오더 엑셀 DTO
 */
@Getter
@Setter
public class ReturnExMap {
    private static Map<String, String> titleMap = Map.ofEntries(
            entry("no", "no"),
            entry("dateArrival", "입항일자"),
            entry("orderAccount","오더거래처"),
            entry("agency", "대리점"),
            entry("deliveryCom", "택배사"),
            entry("number", "송장번호"),
            entry("originNumber", "원송장번호"),
            entry("qty", "수량"),
            entry("price", "물품가액"),
            entry("goodsName", "품명"),
            entry("tel", "번호"),
            entry("addr", "주소"),
            entry("addrDetail", "상세주소"),
            entry("zipNo", "우편번호")
    );

    public static Map<String, String> getReturnOrderEx() {
        return titleMap;
    }
}
