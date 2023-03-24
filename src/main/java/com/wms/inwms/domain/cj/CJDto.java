package com.wms.inwms.domain.cj;

import com.wms.inwms.domain.base.BaseModel;
import lombok.Builder;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "g_change")
@Entity
@Builder
public class CJDto extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "order_dt")
    private String orderNo;

    @Column(name = "shipper_name")
    private String shipperName;

    @Column(name = "created_cj")
    private Instant createdCj;

    @Column(name = "consignee_name")
    private String consigneeNameImp;

    @Column(name = "consignee_address")
    private String consigneeAddressImp;

    @Column(name = "consignee_zip")
    private String consigneeZip;

    @Column(name = "consignee_tel")
    private String consigneeTel;

    @Column(name = "gng_number")
    private String gngNumber;

    @Column(name = "hwb_no")
    private String hwbNo;

    @Column(name = "print_data")
    private String printData;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public void setCreatedCj(Instant createdCj) {
        this.createdCj = createdCj;
    }

    public void setConsigneeNameImp(String consigneeNameImp) {
        this.consigneeNameImp = consigneeNameImp;
    }

    public void setConsigneeAddressImp(String consigneeAddressImp) {
        this.consigneeAddressImp = consigneeAddressImp;
    }

    public void setConsigneeZip(String consigneeZip) {
        this.consigneeZip = consigneeZip;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public void setGngNumber(String gngNumber) {
        this.gngNumber = gngNumber;
    }

    public void setHwbNo(String hwbNo) {
        this.hwbNo = hwbNo;
    }

    public void setPrintData(String printData) {
        this.printData = printData;
    }

    public CJDto() {}

    public CJDto(Long id, String orderNo, String shipperName, Instant createdCj, String consigneeNameImp, String consigneeAddressImp, String consigneeZip, String consigneeTel, String gngNumber, String hwbNo, String printData) {
        this.id = id;
        this.orderNo = orderNo;
        this.shipperName = shipperName;
        this.createdCj = createdCj;
        this.consigneeNameImp = consigneeNameImp;
        this.consigneeAddressImp = consigneeAddressImp;
        this.consigneeZip = consigneeZip;
        this.consigneeTel = consigneeTel;
        this.gngNumber = gngNumber;
        this.hwbNo = hwbNo;
        this.printData = printData;
    }
    public Long getId() {
        return this.id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public String getShipperName() {
        return this.shipperName;
    }

    public Instant getCreatedCj() {
        return this.createdCj;
    }

    public String getConsigneeNameImp() {
        return this.consigneeNameImp;
    }

    public String getConsigneeAddressImp() {
        return this.consigneeAddressImp;
    }

    public String getConsigneeZip() {
        return this.consigneeZip;
    }

    public String getConsigneeTel() {
        return this.consigneeTel;
    }

    public String getGngNumber() {
        return this.gngNumber;
    }

    public String getHwbNo() {
        return this.hwbNo;
    }

    public String getPrintData() {
        return this.printData;
    }
}