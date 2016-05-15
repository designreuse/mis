package gr.athtech.mis.web;

import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.PaidVisitRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import gr.athtech.mis.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@Controller
public class WelcomeController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;
    @Autowired
    private PaidVisitRepository paidVisitRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Principal principal, Model model) {

        int doctors = doctorRepository.findAll().size();
        int medicalVisitors = userRepository.getMedicalVisitors().size();
        int scheduledVisits = scheduledVisitRepository.findAll().size();
        int paidVisits = paidVisitRepository.findAll().size();

        model.addAttribute("doctors", doctors);
        model.addAttribute("medicalVisitors", medicalVisitors);
        model.addAttribute("scheduledVisits", scheduledVisits);
        model.addAttribute("paidVisits", paidVisits);
        
        return "index";
    }

}
