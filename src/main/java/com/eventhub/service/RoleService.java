package com.eventhub.service;

import com.eventhub.entity.Role;

public interface RoleService {
    Role findByName(String name);
}