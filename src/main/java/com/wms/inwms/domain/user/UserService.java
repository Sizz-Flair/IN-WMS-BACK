package com.wms.inwms.domain.user;


import com.querydsl.core.Tuple;
import com.wms.inwms.domain.agent.QAgent;
import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@Slf4j
public class UserService extends BaseService<User, Long> {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public Optional<User> findByUserName(String username) {
        return this.userRepository.findByUserCredentialsUsername(username);
    }

//    public Optional<Tuple> findByUserAllInfoList(String userId) {
//     this.select()
//                .from(QUser.user)
//                .leftJoin(QAgent.agent)
//                .on(QUser.user.id.eq(QAgent.agent.id))
//                .where(QUser.user.name.eq(userId)).fetch();
//    }


}
