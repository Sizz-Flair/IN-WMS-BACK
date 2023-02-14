package com.wms.inwms.security;

import com.wms.inwms.domain.user.User;
import com.wms.inwms.domain.user.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.findByUserName(username);
        return (UserDetails)getUserDetails(user.get());
    }

    private org.springframework.security.core.userdetails.User getUserDetails(User u) {
        return new org.springframework.security.core.userdetails.User(
                u.getUserCredentials().getUsername(),
                u.getUserCredentials().getPassword(),
                getGrantedAuthorities(u));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User u) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(u.getUserCredentials().getRole());
    }
}
