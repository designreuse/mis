/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.PaidVisitRepository;
import gr.athtech.mis.repository.RoleRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import gr.athtech.mis.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author JurgenPC
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PaidVisitRepository paidVisitRepository;
    @Autowired 
    private ScheduledVisitRepository scheduledVisitRepository;

    /**
     * Return the view that will display all the users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<User> users = userRepository.findAll();

        logger.debug("------------------USERS");
        model.put("users", users);

        return "users/view";
    }

    /**
     * Return the view that holds the create new user form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        //fetch all the attributes that will be prefilled
        List<Role> roles = roleRepository.findAll();

        model.addAttribute("roles", roles);

        return "users/create";
    }

    /**
     * Store a new user
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request, HttpServletResponse response, Model model) {

        Role role = roleRepository.findOne(Long.parseLong(request.getParameter("roleId")));
        //fixes the save problem, also we remove cascade.ALL from the user model
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setEnabled(true);
        user.setRoles(roles);

        logger.debug("----- New user: ", user);

        userRepository.save(user);
        return "redirect:/users/";
    }

    /**
     * Return the view that holds the edit user form
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {

        List<Role> roles = roleRepository.findAll();
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return "users/edit";
    }

    /**
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(HttpServletRequest request, HttpServletResponse response, Model model) {

        Role role = roleRepository.findOne(Long.parseLong(request.getParameter("roleId")));
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setId(Long.parseLong(request.getParameter("id")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setRoles(roles);

        userRepository.update(user);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        userRepository.delete(id);

        return "redirect:/users/";
    }
    
    /**
     * Show one user
     * 
     * @param id
     * @param model
     * @return 
     */
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public String one(@PathVariable("id") Long id, Model model) {

        User user = userRepository.findOne(id);
        List<ScheduledVisit> userVisits = scheduledVisitRepository.getAllByVisitorId(id);
        List<PaidVisit> paidVisits = paidVisitRepository.getAllUserVisits(id);
        

        model.addAttribute("user", user);
        model.addAttribute("userVisits", userVisits);
        model.addAttribute("paidVisits", paidVisits);
        

        return "users/one";
    }

}
