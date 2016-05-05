/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.service.PaidVisitService;
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
@RequestMapping(value = "/PaidVisits")
public class PaidVisitController {
    
    private final Logger logger = LoggerFactory.getLogger(PaidVisitController.class);
    
    @Autowired
    private PaidVisitService paidVisitService;
    
    /**
     * Return the view that will display all the scheduled visits
     *
     * @param model
     * @return
     */
     @RequestMapping(value = "/", method = RequestMethod.GET)
     public String index(Map<String, Object> model){
         
         List<PaidVisit> paidVisits = paidVisitService.findAll();
         logger.debug("------------------NEW VISITS");
         model.put("paidVisits", paidVisits);
         return "paidVisits/view";    
     }
    
}
