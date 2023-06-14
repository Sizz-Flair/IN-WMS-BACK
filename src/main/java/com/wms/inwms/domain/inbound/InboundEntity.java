package com.wms.inwms.domain.inbound;

import com.wms.inwms.domain.base.BaseModel;
import com.wms.inwms.domain.inbound.dto.InboundResultDto;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.upperlocation.UpperLocation;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

/**
 * packageName    : com.wms.inwms.domain.inbound
 * fileName       : Inbound
 * author         : akfur
 * date           : 2023-06-12
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="inbound")
public class InboundEntity extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="state")
    private String state;

    @OneToOne
    @JoinColumn(name="upper_location_id")
    private UpperLocation upperLocation;

    @OneToOne
    @JoinColumn(name = "lower_location_id")
    private LowerLocation lowerLocation;

    @Column(name="m_inbound_id")
    private Long mInboundId;

    @Column(name="mapping_num")
    private String mappingNum;

    @Column(name="inbound_date")
    private Instant inboundDate;

    @Column(name="inbound_time")
    private Instant inboundTime;

    @Override
    public Long getId() {
        return this.id;
    }

    public InboundResultDto.InboundSaveResultDto covertResultDto() {
        return InboundResultDto.InboundSaveResultDto.builder()
                .number(this.number)
                .state(this.state)
                .upperLocation(this.upperLocation.getUpLocationName())
                .lowerLocation(this.lowerLocation.getLowLocationName())
                .mappingNum(this.mappingNum).build();
    }
}
