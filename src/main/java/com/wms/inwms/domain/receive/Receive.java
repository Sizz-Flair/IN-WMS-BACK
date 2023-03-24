package com.wms.inwms.domain.receive;

import com.wms.inwms.domain.base.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receive")
@Setter
@Getter
public class Receive extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "location_id")
    private Long locationId;
    @Column(name="receive_number")
    private String receiveNumber;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "expiration_date")
    private Instant expirationDate;
    @Override
    public Long getId() {
        return id;
    }
}
