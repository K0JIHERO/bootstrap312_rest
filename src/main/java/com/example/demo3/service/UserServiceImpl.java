package com.example.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo3.dao.UserDao;
import com.example.demo3.model.Role;
import com.example.demo3.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User findByLastName(String lastname) {
        return userDao.findByLastName(lastname);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByLastName(s);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return new org.springframework.security.core.userdetails.User(user.getLastName(),
                user.getPassword(), convRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> convRoles(Collection<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getRole())).collect(Collectors.toList());
    }
}