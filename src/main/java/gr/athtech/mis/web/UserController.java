/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.User;
import gr.athtech.mis.service.RoleService;
import gr.athtech.mis.service.UserService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JurgenPC
 */
@Controller 
@RequestMapping(value = "/users")
public class UserController {
    
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired 
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /**
     * Return the view that will display all the users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model){
        
        List<User> users = userService.findAll();
        
        logger.debug("------------------USERS");
        model.put("users", users);
        
        return "users/view";
    }
    
    
}
