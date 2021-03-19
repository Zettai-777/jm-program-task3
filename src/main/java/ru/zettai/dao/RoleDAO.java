package ru.zettai.dao;

import org.springframework.stereotype.Repository;
import ru.zettai.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAO{

    @PersistenceContext
    EntityManager entityManager;

    public List<Role> getAllRoles(){
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }

    public void saveOrUpdateRole(Role role){
        Role currentRole = entityManager.merge(role);
        role.setId(currentRole.getId());
    }

    public Role findRoleById(long id){
        return entityManager.find(Role.class, id);
    }

    public void deleteRoleById(long id){
        Query query = entityManager.createQuery("delete from Role where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Role getRoleByName(String role){
        Query query = entityManager.createQuery("from Role where role=:role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

}
