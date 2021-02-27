package com.example.demo3.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo3.model.Role;
import com.example.demo3.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByLastName(String lastname) {
//        Session session = entityManager.unwrap(Session.class);
//        TypedQuery<User> query = session.createQuery("SELECT u FROM User u WHERE u.lastName = :username", User.class);
//        query.setParameter("username", lastname);
//        return query.getSingleResult();
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.lastName = :username", User.class);
        return query.setParameter("username", lastname).getSingleResult();
    }


    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> list;
        list = entityManager.createQuery("from User").getResultList();
        return list;
    }

    @Override
    public void saveUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
        entityManager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
        entityManager.merge(user);
    }

}