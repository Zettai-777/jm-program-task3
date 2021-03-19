package ru.zettai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zettai.dao.RoleDAO;
import ru.zettai.entities.Role;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public void saveOrUpdateRole(Role role) {
        roleDAO.saveOrUpdateRole(role);
    }

    @Override
    public Role findRoleById(long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public void deleteRoleById(long id) {
        roleDAO.deleteRoleById(id);
    }

    @Override
    public Role getRoleByName(String role) {
        return roleDAO.getRoleByName(role);
    }
}
