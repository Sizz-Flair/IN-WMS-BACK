package com.wms.inwms.domain.location.upperlocation;

import com.wms.inwms.domain.base.BaseModel;
import com.wms.inwms.domain.inbound.InboundEntity;
import com.wms.inwms.domain.location.lowerlocation.LowerLocation;
import com.wms.inwms.domain.location.upperlocation.dto.UpperLocationDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * packageName    : com.wms.inwms.domain.location.upperlocation
 * fileName       : UpperLocation
 * author         : akfur
 * date           : 2023-06-13
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="upper_location")
public class UpperLocation extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "up_location_name")
    private String upLocationName;

    @Column(name = "up_location_type")
    private String upLocationType;

    @Column(name = "agent_code")
    private String agentCode;

    @OneToMany(mappedBy = "upperLocation", fetch = FetchType.LAZY)
    private List<LowerLocation> lowerLocationList;

    @OneToMany(mappedBy = "upperLocation", fetch = FetchType.LAZY)
    private List<InboundEntity> inboundEntityList;

    @Override
    public Long getId() {
        return this.id;
    }

    public UpperLocationDto.UpperLocationResultDto convertUpperDto() {
        return UpperLocationDto.UpperLocationResultDto.builder()
                .locationName(upLocationName)
                .locationType(upLocationType)
                .lowLcCount(lowerLocationList.size())
                .inboundDataCount(inboundEntityList.size()).build();
       }
}
