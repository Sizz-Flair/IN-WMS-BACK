package com.wms.inwms.domain.location.upperlocation;

import com.wms.inwms.domain.base.BaseModel;
import lombok.*;

import javax.persistence.*;

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

    @Override
    public Long getId() {
        return this.id;
    }
}
