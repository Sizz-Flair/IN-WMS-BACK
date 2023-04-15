package com.wms.inwms.domain.agent;

import com.wms.inwms.domain.base.BaseModel;
import com.wms.inwms.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="agent")
public class Agent extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="agent_name")
    private String agentName;

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();
}
