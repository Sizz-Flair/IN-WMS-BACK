package com.wms.inwms.domain.user;

import com.wms.inwms.domain.base.BaseRepo;
import com.wms.inwms.domain.user.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepo<User, Long> {
    Optional<User> findByUserCredentialsUsername(String paramString);
}
