package com.wms.inwms.domain.location.lowerlocation;

import com.wms.inwms.domain.base.BaseModel;
import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.wms.inwms.domain.location.lowerlocation
 * fileName       : LowerLocation
 * author         : akfur
 * date           : 2023-06-13
 */
@Entity
@Table(name="lower_location")
@Getter
public class LowerLocation extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "low_location_name")
    private String lowLocationName;

    @Column(name = "low_location_type")
    private String lowLocationType;

    @Column(name = "low_location_pri")
    private String lowLocationPri;

    @Column(name = "upper_location_id")
    private Long uppperLocationId;

    @Override
    public Long getId() {
        return this.id;
    }
}
