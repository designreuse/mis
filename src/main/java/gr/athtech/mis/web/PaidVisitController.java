/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.repository.CycleRepository;
import gr.athtech.mis.repository.PaidVisitRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping(value = "/paidVisits")
public class PaidVisitController {
    
    private final Logger logger = LoggerFactory.getLogger(PaidVisitController.class);
    
    @Autowired
    private PaidVisitRepository paidVisitRepository;
    @Autowired 
    private ScheduledVisitRepository scheduledVisitRepository;
    @Autowired
    private CycleRepository cycleRepository;
    
    /**
     * Return the view that will display all the paid visits
     *
     * @param model
     * @return
     */
     @RequestMapping(value = "/", method = RequestMethod.GET)
     public String index(Map<String, Object> model){
         
         List<PaidVisit> paidVisits = paidVisitRepository.findAll();
         logger.debug("------------------NEW VISITS");
         model.put("paidVisits", paidVisits);
         return "paidVisits/view";    
     }
     
     
     /**
     * Display only the cycles drop down 
     * @param model
     * @return 
     */
    
    @RequestMapping(value = "/allCycles", method = RequestMethod.GET)
    public String showCycles(Map<String, Object> model) {

        List<PaidVisit> paidVisits = paidVisitRepository.getAllPaidVisitsByCurrentCycle();
        List<Cycle> cyclesList = cycleRepository.findAll();
        logger.debug("------------------NEW VISITS");
        model.put("paidVisits", paidVisits);
        model.put("cyclesList", cyclesList); 
        return "paidVisits/byCycle";

    }
     
     /**
     * Display the paid visits of the selected cycle
     * @param request
     * @param response
     * @param model
     * @return 
     */
    @RequestMapping(value = "/byCycle", method = RequestMethod.POST)
    public String showSelectedVisits(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {
        
        Long id = Long.parseLong(request.getParameter("cycleId"));
        List<Cycle> cyclesList = cycleRepository.findAll();
        List<PaidVisit> paidVisits = paidVisitRepository.getAllPaidVisitsByCycle(id);
        model.put("cyclesList", cyclesList);
        model.put("paidVisits", paidVisits);
        return "paidVisits/byCycle";
    }
     
     /**
     * Return the view that will display all the paid visits of the logged in user
     *
     * @param id
     * @param model
     * @return
     */
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String indexSingle(@PathVariable("id") Long id, Map<String, Object> model){
         
         List<PaidVisit> paidVisits = paidVisitRepository.getAllUserVisitsByCurrentCycle(id);
         logger.debug("------------------NEW VISITS");
         model.put("paidVisits", paidVisits);
         return "paidVisits/view";    
     }
     
     /**
     * Return the view that holds the edit scheduled visit form
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String create(@PathVariable("id") Long id, Model model) {

        ScheduledVisit schv = scheduledVisitRepository.findById(id);
        model.addAttribute("schv", schv);
        return "paidVisits/create";
    }
    
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        ScheduledVisit selectedSchv = scheduledVisitRepository.findById(Long.parseLong(request.getParameter("id")));
        selectedSchv.setStatus("Paid");
        
        //Convert date parammeter to SQL date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = format.parse(request.getParameter("date"));
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //store paid visit in the database
        PaidVisit pdvst = new PaidVisit();
        pdvst.setWeek(request.getParameter("week"));
        pdvst.setHour(request.getParameter("hour"));
        pdvst.setIsGroup(request.getParameter("group"));
        pdvst.setDate(sqlDate);
        pdvst.setComments(request.getParameter("comment"));
        pdvst.setScheduledVisit(selectedSchv);
        
        logger.debug("----- New user: ", pdvst);

        paidVisitRepository.save(pdvst);
        return "redirect:/paidVisits/";
    }
    
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String info(@PathVariable("id") Long id, Model model) {

        PaidVisit paidVisit = paidVisitRepository.findById(id);
        model.addAttribute("paidVisit", paidVisit);
        return "paidVisits/info";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        paidVisitRepository.delete(id);
        logger.debug("------------------PAID VISIT DELETED");

        return "redirect:/paidVisits/";
    }
    
    
     
     
    
}
