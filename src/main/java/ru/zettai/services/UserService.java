package ru.zettai.services;

import org.springframework.transaction.annotation.Transactional;
import ru.zettai.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean saveUser(User user);

    void updateUser(User user);

    User findUserById(long id);

    void deleteUserById(long id);

    User getUserByUsername(String username);


}
