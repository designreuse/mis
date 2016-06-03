/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.web;

import gr.athtech.mis.model.ExtraVisit;
import gr.athtech.mis.repository.ExtraVisitRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xrist
 */
@Controller
@RequestMapping(value = "/extraVisits")
public class ExtraVisitController {

    @Autowired
    private ExtraVisitRepository extraVisitRepository;
    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;

    @RequestMapping(value = "/store/{id}", method = RequestMethod.POST)
    public String store(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        //Convert date parammeter to SQL date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fromDate = format.parse(request.getParameter("date"));
        java.sql.Date date = new java.sql.Date(fromDate.getTime());

        ExtraVisit extraVisit = new ExtraVisit();
        extraVisit.setComments(request.getParameter("comments"));
        extraVisit.setTime(request.getParameter("time"));
        extraVisit.setScheduledVisit(scheduledVisitRepository.findById(Long.parseLong(request.getParameter("scheduledVisitId"))));
        extraVisit.setDate(date);
        extraVisitRepository.save(extraVisit);

        return "redirect:/scheduledVisits/{id}";
    }

}
