package com.example.demo3.service;

import com.example.demo3.model.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getRoles(Set<String> role);

    Role getRole(String name);

    Set<Role> findAllRoles();
}
