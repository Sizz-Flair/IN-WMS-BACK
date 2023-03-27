package com.wms.inwms.domain.agent;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AgentService extends BaseService<Agent, Long> {

    private AgentRepository repository;
    public static List<Agent> agentInfo;
    public AgentService(AgentRepository repository) {
        super(repository);
        this.repository = repository;
    }
    @PostConstruct
    private void setAgentInfo() {
        this.agentInfo = this.repository.findAll();
        System.out.println(agentInfo);
    }
}
