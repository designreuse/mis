package gr.athtech.mis.web;

import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.UserRepository;
import gr.athtech.mis.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Principal principal, Model model) {

        List<User> medicalVisitors = userRepository.getMedicalVisitors();

        model.addAttribute("medicalVisitors", medicalVisitors);

        return "reports/view";
    }

    @RequestMapping(value = "/byGeolocation", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Integer> byGeolocation(HttpServletRequest request) {

        Map<String, Integer> byGeolocation = userService.byGeolocationArea(Long.parseLong(request.getParameter("medicalVisitorId")));

        return byGeolocation;
    }

}
