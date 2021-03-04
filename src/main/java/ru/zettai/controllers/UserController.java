package ru.zettai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zettai.entities.User;
import ru.zettai.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showAllUsers(Model model){
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @GetMapping("/addUser")
    public String addNewUser(@ModelAttribute("user") User user, Model model){
        userService.saveOrUpdateUser(user);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PutMapping("/updateUserInfo")
    public String updateUser(@RequestParam(name = "userId") long id, Model model){
        User currentUser = userService.findUserById(id);
        model.addAttribute("user", currentUser);
        userService.saveOrUpdateUser(currentUser);
        return "redirect:/all-users";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveOrUpdateUser(user);
        return "redirect:/all-users";
    }

    @DeleteMapping("/deleteUserById")
    public String deleteUser(@ModelAttribute(name = "user") User user){
        long id = user.getId();
        userService.deleteUserById(id);
        return "redirect:/all-users";
    }

}
