package com.example.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo3.dao.RoleDao;
import com.example.demo3.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Set<Role> getRoles(Set<String> role) {
        return roleDao.getRoles(role);
    }

    @Transactional
    @Override
    public Role getRole(String name) {
        return roleDao.getRole(name);
    }

    @Transactional
    @Override
    public Set<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }
}
