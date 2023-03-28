package com.wms.inwms.domain.user;

import com.wms.inwms.domain.agent.Agent;
import com.wms.inwms.domain.base.BaseModel;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "g_user")
public class User extends BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Column(name = "use_yn")
    private String useYn = "Y";

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Embedded
    private UserCredentials userCredentials;

    public String getUseYn() {
        return this.useYn;
    }

    public UserCredentials getUserCredentials() {
        return this.userCredentials;
    }
}