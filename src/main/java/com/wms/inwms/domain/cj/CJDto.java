package com.wms.inwms.domain.cj;

import lombok.*;
import org.json.simple.JSONObject;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CJDto {
    private String consigneeName;
    private String consigneeAddress;
    private String consigneeTel;
    private String consigneeZipCode;

    private String hwbNo;

    private String gngNumber;
    private String cjNumber;

    private String printLabel1;
    private String printLabel2;
    private String printLabel3;
    private String printLabel4;
    private String printLabel5;
    private String printLabel6;
    private String printLabel7;

}
