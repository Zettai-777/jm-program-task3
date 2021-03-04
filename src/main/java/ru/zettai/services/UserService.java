package ru.zettai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zettai.dao.UserDAO;
import ru.zettai.entities.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public void saveOrUpdateUser(User user){
        userDAO.saveOrUpdateUser(user);
    }

    @Transactional
    public User findUserById(long id){
        return userDAO.findUserById(id);
    }

    @Transactional
    public void deleteUserById(long id){
        userDAO.deleteUserById(id);
    }

}
