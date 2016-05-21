package gr.athtech.mis.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String index(HttpServletRequest request, Principal principal, Map<String, Object> model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("medical1234");
        return hashedPassword;
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public void date(HttpServletRequest request, Principal principal, Map<String, Object> model) throws ParseException {
        Calendar dCal = Calendar.getInstance();
        dCal.setTime(new Date());
        dCal.set(Calendar.HOUR_OF_DAY, 0);
        dCal.set(Calendar.MINUTE, 0);
        dCal.set(Calendar.SECOND, 0);
        dCal.set(Calendar.MILLISECOND, 0);

        logger.debug("======= date: {}", dCal.getTime());
    }

}
