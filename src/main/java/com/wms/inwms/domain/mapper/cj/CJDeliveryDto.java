package com.wms.inwms.domain.mapper.cj;

import lombok.*;

import javax.persistence.Column;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJDeliveryDto {
    @Builder.Default
    @Column(name = "CUST_ID") // 업체ID
    private String custId = "30327633"; //"30327633";

    @Builder.Default
    @Column(name = "RCPT_YMD") // 현재날짜
    private String rcptYmd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));

    @Column(name = "CUST_USE_NO")
    private String custUseNo;

    @Builder.Default
    @Column(name = "RCPT_DV") // 접수구분
    private String rcptDv = "02"; // 01:일반, 02:반품

    @Builder.Default
    @Column(name = "WORK_DV_CD") // 작업구분코드
    private String workDvCd = "01"; // 01:일반, 02:교환, 03:A/S

    @Builder.Default
    @Column(name = "REQ_DV_CD") // 요청구분코드
    private String reqDvCd = "01"; // 01:요청, 02:취소

    @Column(name = "MPCK_KEY") // 합포장키
    private String mpckKey; // YYYYMMdd + "_30244464" + 운송장번호

    @Builder.Default
    @Column(name = "MPCK_SEQ") // 합포장순서
    private int mpckSeq = 1;

    @Builder.Default
    @Column(name = "CAL_DV_CD") // 정산구분코드
    private String calDvCd = "01"; // 01:계약운임, 02:자료운임

    @Builder.Default
    @Column(name = "FRT_DV_CD") // 운임구분코드
    private String frtDvCd = "03"; // 01:선불, 02:착불, 03:신용

    @Builder.Default
    @Column(name = "CNTR_ITEM_CD") // 계약품목코드
    private String cntrItemCd = "01"; // 01:일반품목

    @Builder.Default
    @Column(name = "BOX_TYPE_CD") // 박스타입코드
    private String boxTypeCd = "01"; // 01:극소, 02:소, 03:중, 04:대, 05:특대

    @Builder.Default
    @Column(name = "BOX_QTY") // 박스개수
    private int boxQty = 1;

    @Builder.Default
    @Column(name = "CUST_MGMT_DLCM_CD") // 고객관리거래처코드
    private String custMgmtDlcmCd = "30327633"; //"30327633";

    @Column(name = "SENDR_NM") // 송항인명
    private String sendrNm;

    @Column(name = "SENDR_TEL_NO1") // 송하인번호1 0으로시작할것
    private String sendrTelNo1;

    @Column(name = "SENDR_TEL_NO2") // 송하인번호2
    private String sendrTelNo2;

    @Column(name = "SENDR_TEL_NO3") // 송하인번호3
    private String sendrTelNo3;

    @Builder.Default
    @Column(name = "SENDR_CELL_NO1") // 송하인번호1 0으로시작할것
    private String sendrCellNo1 = "";

    @Builder.Default
    @Column(name = "SENDR_CELL_NO2") // 송하인번호2
    private String sendrCellNo2 = "";

    @Builder.Default
    @Column(name = "SENDR_CELL_NO3") // 송하인번호3
    private String sendrCellNo3 = "";

    @Column(name = "SENDR_ZIP_NO") // 송하인 우편번호
    private String sendrZipNo;

    @Column(name = "SENDR_ADDR") // 송하인 우편번호
    private String sendrAddr;

    @Column(name = "SENDR_DETAIL_ADDR") // 송하인 우편번호
    private String sendrDetailAddr;

    @Builder.Default
    @Column(name = "RCVR_NM") // 수하인명
    private String rcvrNm = "(주)자이언트네트워크그룹";

    @Builder.Default
    @Column(name = "RCVR_TEL_NO1") // 수하인번호1 0으로시작할것
    private String rcvrTelNo1 = "070";

    @Builder.Default
    @Column(name = "RCVR_TEL_NO2") // 수하인번호2
    private String rcvrTelNo2 = "7784";

    @Builder.Default
    @Column(name = "RCVR_TEL_NO3") // 수하인번호3
    private String rcvrTelNo3 = "8155";

    @Column(name = "RCVR_CELL_NO1") // 수하인번호1 0으로시작할것
    private String rcvrCellNo1;

    @Column(name = "RCVR_CELL_NO2") // 수하인번호2
    private String rcvrCellNo2;

    @Column(name = "RCVR_CELL_NO3") // 수하인번호3
    private String rcvrCellNo3;

    @Builder.Default
    @Column(name = "RCVR_ZIP_NO") // 수하인 우편번호
    private String rcvrZipNo = "22379";

    @Builder.Default
    @Column(name = "RCVR_ADDR") // 수하인 주소
    private String rcvrAddr = "인천광역시 중구 운서동 3167-7";

    @Builder.Default
    @Column(name = "RCVR_DETAIL_ADDR") // 수하인 상세주소
    private String rcvrDetailAddr = "자이언트네트워크 인천국제물류센터";

    @Column(name="INVC_NO") // 송장번호
    private String invcNo ;

    @Builder.Default
    @Column(name = "PRT_ST") // 출력상태
    private String prtSt = "01"; // 01:미출력, 02:선출력, 03:선발선

    @Column(name = "GDS_NM") // 상품명
    private String gdsNm;

    @Column(name = "GDS_QTY") // 내품수량
    private Long gdsQty;

    @Builder.Default
    @Column(name = "DLV_DV") // 택배구분
    private String dlvDv = "01"; // 01:택배, 02:중량물(설치물류), 03:중량물(비설치류)

    @Builder.Default
    @Column(name = "RCPT_ERR_YN") // 접수에러여부
    private String rcptErrYn = "N";

    @Builder.Default
    @Column(name = "EAI_PRGS_ST") // EAI전송상태
    private String eaiPrgsSt = "01";  // DEFAULT "01"

    @Builder.Default
    @Column(name = "REG_EMP_ID") // 등록 ID
    private String regEmpId = "GIANTNET";

    @Builder.Default
    @Column(name = "REG_DTIME") // 등록일자
    private Instant regDtime = Instant.now(Clock.systemUTC());

    @Builder.Default
    @Column(name = "MODI_EMP_ID") // 수정 ID
    private String modiEmpId = "GIANTNET";

    @Builder.Default
    @Column(name = "MODI_DTIME")
    private Instant modiDtime = Instant.now(Clock.systemUTC());

    @Builder.Default
    @Column(name = "REMARK_1") // 배송 메세지
    private String remark1 = "";

    @Builder.Default
    @Column(name = "ORI_INVC_NO") // 반품 - 원운송장 번호
    private String oriInvcNo = "";

    @Builder.Default
    @Column(name = "ORI_ORD_NO") // 반품 - 원 주문 번호
    private String oriOrdNo = "";
}
