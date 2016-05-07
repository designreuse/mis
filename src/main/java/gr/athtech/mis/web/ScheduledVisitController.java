/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.service.CycleService;
import gr.athtech.mis.service.DoctorService;
import gr.athtech.mis.service.ScheduledVisitService;
import gr.athtech.mis.service.UserService;
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
    private ScheduledVisitService scheduledVisitsService;
    @Autowired
    private CycleService cycleService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;
    
    /**
     * Return the view that will display all the scheduled visits
     *
     * @param model
     * @return
     */
     @RequestMapping(value = "/", method = RequestMethod.GET)
     public String index(Map<String, Object> model){
         
         List<ScheduledVisit> newVisits = scheduledVisitsService.findAll();
         logger.debug("------------------NEW VISITS");
         model.put("newVisits", newVisits);
         return "scheduledVisits/view";
         
     }
     
     /**
     * Return the view that holds the create a new scheduled visit form
     *
     * @param model
     * @return
     */
     @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String store(Model model){
        
        //fetch all the attributes that wil be prefilled
        List<Cycle> cycles = cycleService.findAll();
        List<User> visitors = userService.getMedicalVisitors();
        List<Doctor> doctors = doctorService.getAvailableDoctorList(); //show only doctors that are not included in any scheduled visit
        
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

        Cycle cycle = cycleService.findOne(Long.parseLong(request.getParameter("cycleId")));
        User visitor = userService.findById(Long.parseLong(request.getParameter("medicalVisitorId")));
        Doctor doctor = doctorService.findOne(Long.parseLong(request.getParameter("doctorId")));

        ScheduledVisit schvst = new ScheduledVisit();
        schvst.setCycle(cycle);
        schvst.setMedicalVisitor(visitor);
        schvst.setDoctor(doctor);
        schvst.setStatus("Pending");

        logger.debug("----- New user: ", schvst);

        scheduledVisitsService.save(schvst);
        return "redirect:/ScheduledVisits/";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        scheduledVisitsService.delete(id);

        return "redirect:/ScheduledVisits/";
    }
    
    /**
     * Return the view that will display all the scheduled visits for the logged in user
     *
     * @param model
     * @return
     */
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String displayByUser(@PathVariable("id") Long id, Map<String, Object> model){
         
         List<ScheduledVisit> newVisits = scheduledVisitsService.getAllByVisitorId(id);
         logger.debug("------------------NEW VISITS");
         model.put("newVisits", newVisits);
         return "scheduledVisits/view";
         
     }
      
}