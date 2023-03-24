package com.wms.inwms.domain.receive.receivelist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hwb_no")
    private String hwbNo;

    @Column(name="receive_number")
    private String receiveNumber;

}
