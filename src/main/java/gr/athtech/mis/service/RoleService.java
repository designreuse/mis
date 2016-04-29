/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.service;

import gr.athtech.mis.model.Role;
import gr.athtech.mis.repository.RoleRepository;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author JurgenPC
 */
@Service("roleService")
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Resource
    RoleRepository repo;

    /**
     * Return a list of all the roles
     *
     * @return
     */
    public List<Role> findAll() {
        List<Role> roles = repo.findAll();

        return roles;
    }

    /**
     * Return a role based on a given id
     *
     * @param id
     * @return
     */
    public Role findOne(Long id) {
        Role role = repo.findOne(id);

        return role;
    }

    /**
     * Return a role based on a given name
     * 
     * @param name
     * @return 
     */
    public Role findByName(String name) {
        Role role = repo.findByName(name);
        return role;
    }

}
