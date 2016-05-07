package gr.athtech.mis.web;

import gr.athtech.mis.model.SecurityUser;
import gr.athtech.mis.service.AuthService;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Principal principal, Map<String, Object> model) {

        HttpSession session = request.getSession(true); //create a new session

        return "index";
    }

}
