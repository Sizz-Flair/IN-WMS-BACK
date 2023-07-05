package com.wms.inwms.domain.base;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class BaseModel<PK extends Serializable> implements Persistable<PK>, Serializable {
    @Version
    @Column(name="version", nullable = false)
    protected int version;
    @Column(updatable = false)
    public Instant created;

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isNew() {
        return (null == getId());
    }

    @PrePersist
    protected void onPersist() {
        this.created = Instant.now(Clock.systemUTC());
    }
}

