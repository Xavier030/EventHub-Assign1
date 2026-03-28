package com.eventhub.service;

import com.eventhub.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User save(User user);

    User findByEmail(String email);

    // Spring Security required method
    @Override
    UserDetails loadUserByUsername(String username);
}