package ru.zettai.dao;

import org.hibernate.Session;
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

    public void saveUser(User user){
        entityManager.persist(user);
    }

    public void updateUser(User user){
//        Query query = entityManager.createQuery("update User as u set u.username=:username, u.email=:email where u.id=:id");
//        query.setParameter("username", "");
//        query.setParameter("email", "");
//        query.setParameter("id", user.getId());
//        query.executeUpdate();
//        Session session = entityManager.unwrap(Session.class);
//        User currentUser = findUserById(user.getId());
//        currentUser.setUsername(user.getUsername());
//        currentUser.setPassword(user.getPassword());
//        currentUser.setFirstName(user.getFirstName());
//        currentUser.setSurName(user.getSurName());
//        currentUser.setAge(user.getAge());
//        currentUser.setEmail(user.getEmail());
//        session.saveOrUpdate(currentUser);
        entityManager.merge(user);
//        user.setId(currentUser.getId());
    }

    public User findUserById(long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    public void deleteUserById(long id){
        Query query = entityManager.createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public User getUserByUsername(String username){
        Query query = entityManager.createQuery("from User where username=:username");
        query.setParameter("username", username);
        User currentUser = (User) query.getSingleResult();
        System.out.println(currentUser.getPassword());
        currentUser.getRoles().forEach(role -> System.out.println(role.getRole()));
        return currentUser;
    }
}
