package ru.zettai.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zettai.dao.RoleDAO;
import ru.zettai.entities.Role;
import ru.zettai.entities.User;
import ru.zettai.services.RoleService;
import ru.zettai.services.UserService;

@Component
public class RoleToUserConverter implements Converter<String, Role> {

    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String o) {
        Long id = Long.parseLong(o);
        Role role = roleService.findRoleById(id);
        return role;
    }
}
