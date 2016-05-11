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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mgava
 */
@Controller
@RequestMapping(value = "/cycles")
public class CycleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CycleRepository cycleRepository;

    /**
     * Return the view that will display all the users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<Cycle> cycles = cycleRepository.findAll();

        logger.debug("------------------CYCLES");
        model.put("cycles", cycles);

        return "cycles/view";
    }

    /**
     * Return the view that holds the create new user form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        return "cycles/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        //Convert date parammeter to SQL date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fromDate = format.parse(request.getParameter("fromDate"));
        java.sql.Date sqlfromDate = new java.sql.Date(fromDate.getTime());
        java.util.Date toDate = format.parse(request.getParameter("toDate"));
        java.sql.Date sqltoDate = new java.sql.Date(toDate.getTime());

        Cycle cycle = new Cycle();
        cycle.setStartDate(sqlfromDate);
        cycle.setEndDate(sqltoDate);

        cycleRepository.save(cycle);

        return "redirect:/cycles/";
    }
}
