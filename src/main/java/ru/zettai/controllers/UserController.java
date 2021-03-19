package ru.zettai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zettai.entities.User;
import ru.zettai.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUserInformation(
            Authentication authentication,
            Model model) {
        User currentUser = userService.getUserByUsername(authentication.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("rolse", currentUser.getRoles());
        return "userPage";
    }
}
