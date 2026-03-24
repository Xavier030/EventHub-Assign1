package com.eventhub.service;

import com.eventhub.entity.User;

public interface UserService {
    User findByUsername(String username);
    User save(User user);
    User findByEmail(String email);
}