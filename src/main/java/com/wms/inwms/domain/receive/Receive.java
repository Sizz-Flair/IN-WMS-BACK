package com.wms.inwms.domain.receive;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Receive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "expiration_data")
    private Instant expirationData;

}
