/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.Role;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Uses the IRoleRepository interface
 * 
 * @author JurgenPC
 */
@Service("roleRepository")
public class RoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @Resource
    IRoleRepository repo;

    /**
     * Return a list of all the roles
     *
     * @return List<Role>
     */
    public List<Role> findAll() {
        List<Role> roles = repo.findAll();
        return roles;
    }

    /**
     * Return a role based on a given id
     *
     * @param id
     * @return Role
     */
    public Role findOne(Long id) {
        Role role = repo.findOne(id);

        return role;
    }

    /**
     * Return a role based on a given name
     *
     * @param name
     * @return Role
     */
    public Role findByName(String name) {
        Role role = repo.findByName(name);
        return role;
    }

}
