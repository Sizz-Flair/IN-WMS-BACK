package com.wms.inwms.domain.base;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class BaseModel<PK extends Serializable> implements Persistable<PK>, Serializable {
    private static final long serialVersionUID = 1L;

    @Column(updatable = false)
    protected Instant created;

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

