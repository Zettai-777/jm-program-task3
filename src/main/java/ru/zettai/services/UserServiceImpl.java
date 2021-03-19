package ru.zettai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zettai.dao.UserDAO;
import ru.zettai.entities.Role;
import ru.zettai.entities.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserDAO userDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean saveUser(User user){
        User userFromDB = null;
        try{
            userFromDB= userDAO.getUserByUsername(user.getUsername());
//            System.out.println("User from DB\n" + userFromDB);
        }catch (Exception sqlEx){

        }

        if( userFromDB != null){
            return false;
        }
//        user.setRoles(Collections.singleton(new Role(1L, "USER")));
//        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
        return true;
    }

    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    public User findUserById(long id){
        return userDAO.findUserById(id);
    }

    public void deleteUserById(long id){
        userDAO.deleteUserById(id);
    }

    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = getUserByUsername(username);
        if(currentUser == null){
            throw new UsernameNotFoundException(String.format("User %s not found!", username));
        }
        return currentUser;
    }
}
