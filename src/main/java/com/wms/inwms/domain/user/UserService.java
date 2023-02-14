package com.wms.inwms.domain.user;


import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.base.BaseService;
import com.wms.inwms.domain.user.User;
import com.wms.inwms.domain.user.UserRepository;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {
    private BCryptPasswordEncoder encoder;

    private UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        super((BaseRepo)userRepository);
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public Optional<User> findByUserName(String username) {
        return this.userRepository.findByUserCredentialsUsername(username);
    }
}
