package ru.zettai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zettai.entities.Role;
import ru.zettai.entities.User;
import ru.zettai.services.UserService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting() {
        return "greetingPage";
    }

    @GetMapping("/home")
    public String home(
            Authentication authentication,
            Model model) {
        User currentUser = userService.getUserByUsername(authentication.getName());
        Set<String> rolesName = currentUser.getRoles()
                .stream().map(Role::getAuthority)
                .collect(Collectors.toSet());
        model.addAttribute("rolesName", rolesName);
        return "homePage";
    }

}
