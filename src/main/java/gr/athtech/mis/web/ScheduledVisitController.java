/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.CycleRepository;
import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.GroupRepository;
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
 * @author jmone
 */
@Controller
@RequestMapping(value = "/scheduledVisits")
public class ScheduledVisitController {

    private final Logger logger = LoggerFactory.getLogger(ScheduledVisitController.class);

    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;
    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    

    /**
     * Return the view that will display all the scheduled visits
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        //Show the scheduled visits of the current cycle based on the current date
        List<ScheduledVisit> newVisits = scheduledVisitRepository.showByCurrentCycle();
        logger.debug("------------------NEW VISITS");
        model.put("newVisits", newVisits);

        return "scheduledVisits/view";

    }

    /**
     * Display only the cycles drop down
     *
     * @param model
     * @return
     */

    @RequestMapping(value = "/allCycles", method = RequestMethod.GET)
    public String showCycles(Map<String, Object> model) {

        List<ScheduledVisit> newVisits = scheduledVisitRepository.showByCurrentCycle();
        List<Cycle> cyclesList = cycleRepository.findAll();
        logger.debug("------------------NEW VISITS");
        model.put("newVisits", newVisits);
        model.put("cyclesList", cyclesList);
        return "scheduledVisits/byCycle";

    }

    /**
     * Display the scheduled visits of the selected cycle
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/byCycle", method = RequestMethod.POST)
    public String showSelectedVisits(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {

        Long id = Long.parseLong(request.getParameter("cycleId"));
        List<Cycle> cyclesList = cycleRepository.findAll();
        List<ScheduledVisit> newVisits = scheduledVisitRepository.showVisitsByCycleId(id);
        model.put("cyclesList", cyclesList);
        model.put("newVisits", newVisits);
        return "scheduledVisits/byCycle";
    }

    /**
     * Return the view that holds the create a new scheduled visit form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String store(Model model) {

        //fetch all the attributes that wil be prefilled
        List<Group> groups = groupRepository.findAll();
        List<Cycle> cycles = cycleRepository.findAll();
        List<User> visitors = userRepository.getMedicalVisitors();
        List<Doctor> doctors = doctorRepository.getAvailableDoctorList(); //show only doctors that are not included in any scheduled visit

        model.addAttribute("groups", groups);
        model.addAttribute("cycles", cycles);
        model.addAttribute("visitors", visitors);
        model.addAttribute("doctors", doctors);

        return "scheduledVisits/create";
    }

    /**
     * Store a new scheduled visit
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request, HttpServletResponse response, Model model) {

        Cycle cycle = cycleRepository.findOne(Long.parseLong(request.getParameter("cycleId")));
        User visitor = userRepository.findOne(Long.parseLong(request.getParameter("medicalVisitorId")));
        Doctor doctor = doctorRepository.findOne(Long.parseLong(request.getParameter("doctorId")));

        List<User> users = new ArrayList<>();
        users.add(visitor);

        ScheduledVisit schvst = new ScheduledVisit();
        schvst.setCycle(cycle);
        schvst.setMedicalVisitors(users);
        schvst.setDoctor(doctor);
        schvst.setStatus("Pending");

        logger.debug("----- New user: ", schvst);

        scheduledVisitRepository.save(schvst);
        return "redirect:/scheduledVisits/allCycles";
    }

    /**
     * Store a new scheduled visit
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/storeGroup", method = RequestMethod.POST)
    public String storeGroup(HttpServletRequest request, HttpServletResponse response, Model model) {

        Cycle cycle = cycleRepository.findOne(Long.parseLong(request.getParameter("cycleIdGroup")));
        Group group = groupRepository.findOne(Long.parseLong(request.getParameter("groupVisitorId")));
        Doctor doctor = doctorRepository.findOne(Long.parseLong(request.getParameter("doctorIdGroup")));

        List<Group> groups = new ArrayList<>();
        groups.add(group);

        ScheduledVisit schvst = new ScheduledVisit();

        schvst.setCycle(cycle);
        schvst.setDoctor(doctor);
        schvst.setStatus("Pending");
        schvst.setGroups(groups);

        logger.debug("----- New user: ", schvst);

        scheduledVisitRepository.save(schvst);
        return "redirect:/scheduledVisits/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {

        scheduledVisitRepository.delete(id);
        return "redirect:/scheduledVisits/";
    }

    /**
     * Return the view that will display all the scheduled visits for the logged
     * in user
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String displayByUser(@PathVariable("id") Long id, Map<String, Object> model) {

        User user = userRepository.findOne(id);
        
        //for individual visits
        List<ScheduledVisit> newVisits = scheduledVisitRepository.getUsersFromCurrentCycle(id);
        
        //For group visits
        List<Group> leaders = groupRepository.findByLeader(user);
        List<Group> members = groupRepository.findByUserId(id);
        
        if(leaders.isEmpty()){
            
            Long groupId = groupRepository.findGroupId(id);
            List<ScheduledVisit> newGroupVisits = scheduledVisitRepository.findRelatedMembers(groupId);
            model.put("newGroupVisits", newGroupVisits);
        }
        else if(members.isEmpty()){
            
            List<ScheduledVisit> newGroupVisits = scheduledVisitRepository.getGroupsFromCurrentCycle(id);
            model.put("newGroupVisits", newGroupVisits);
        }
        else{    
            
            List<ScheduledVisit> newGroupVisits = scheduledVisitRepository.findByMemberAndLeader(id);
            model.put("newGroupVisits", newGroupVisits);
        }
        
        logger.debug("------------------NEW VISITS");
        model.put("newVisits", newVisits);
        return "scheduledVisits/view";

    }
}
