package ru.zettai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zettai.dao.UserDAO;
import ru.zettai.entities.User;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

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

    @Transactional
    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = getUserByUsername(username);
        if(currentUser == null){
            throw new UsernameNotFoundException(String.format("User %s not found!", username));
        }
        return new org.springframework.security.core.userdetails.User(
                currentUser.getUsername(),
                currentUser.getPassword(),
                currentUser.getAuthorities()
        );
    }
}
