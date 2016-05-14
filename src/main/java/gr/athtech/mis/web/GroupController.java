/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.GroupRepository;
import gr.athtech.mis.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author JurgenPC
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupController {

    private final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository repo;

    /**
     * Return the view that will display all the groups
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<Group> groups = repo.findAll();

        model.put("groups", groups);

        return "groups/view";
    }

    /**
     * Return the view that holds the create new group form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        List<User> medicalVisitors = userRepository.getMedicalVisitors();
        model.addAttribute("medicalVisitors", medicalVisitors);

        return "groups/create";
    }

    /**
     * Store a new group
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@RequestParam(value = "members[]") String[] memberIds,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        //find the leader of the group
        User leader = userRepository.findOne(Long.parseLong(request.getParameter("leaderId")));

        List<User> members = new ArrayList<>();
        for (String memderId : memberIds) {
            members.add(userRepository.findOne(Long.parseLong(memderId)));
        }

        Group group = new Group();
        group.setName(request.getParameter("groupName"));
        group.setLeader(leader);
        group.setMembers(members);

        repo.save(group);

        return "redirect:/groups/";
    }

    /**
     * Return the view that holds the edit group form
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {

        Group group = repo.findOne(id);
        model.addAttribute("group", group);

        return "groups/edit";
    }

    /**
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam(value = "members[]") String[] memberIds, HttpServletRequest request, HttpServletResponse response, Model model) {

        //find the leader of the group
        User leader = userRepository.findOne(Long.parseLong(request.getParameter("leaderId")));

        List<User> members = new ArrayList<>();
        for (String memderId : memberIds) {
            members.add(userRepository.findOne(Long.parseLong(memderId)));
        }

        Group group = repo.findOne(Long.parseLong(request.getParameter("id")));
        group.setName(request.getParameter("groupName"));
        group.setLeader(leader);
        group.setMembers(members);

        repo.save(group);

        return "redirect:/groups/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        repo.delete(id);

        return "redirect:/groups/";
    }

    /**
     * Show one group
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public String one(@PathVariable("id") Long id, Model model) {

        return "groups  /one";
    }

}
