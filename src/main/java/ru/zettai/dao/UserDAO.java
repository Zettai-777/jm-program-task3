package ru.zettai.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zettai.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

//    @Autowired
//    SessionFactory sessionFactory;

//    public List<User> getAllUsers(){
//        List<User> allUsers = sessionFactory.getCurrentSession().createQuery("from User").list();
//        return allUsers;
//    }

    public List<User> getAllUsers(){
        Query query = entityManager.createQuery("from User");
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    public void saveOrUpdateUser(User user){
        User currentUser = entityManager.merge(user);
        user.setId(currentUser.getId());
    }

    public User findUserById(long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    public void deleteUserById(long id){
        Query query = entityManager.createQuery("delete from User where id=: id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
