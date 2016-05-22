package gr.athtech.mis.web;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.GroupRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import gr.athtech.mis.repository.UserRepository;
import gr.athtech.mis.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;

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

    @RequestMapping(value = "/individualStatistics", method = RequestMethod.GET)
    @ResponseBody
    public List<ScheduledVisit> individualStatistics(HttpServletRequest request) {

        User user = userRepository.findOne(Long.parseLong(request.getParameter("medicalVisitorId")));

        //for individual visits
        List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.getUsersFromCurrentCycle(Long.parseLong(request.getParameter("medicalVisitorId")));

        for (ScheduledVisit sch : scheduledVisits) {
            sch.getDoctor().setInstitutionName(sch.getDoctor().getInstitution().getName());
            sch.getDoctor().setCityName(sch.getDoctor().getCity().getName());
            sch.getDoctor().setGeolocationAreaName(sch.getDoctor().getGeolocationArea().getName());
            sch.getDoctor().setSpecialtyName(sch.getDoctor().getSpecialty().getName());
        }

        return scheduledVisits;
    }

    @RequestMapping(value = "/individualAndGroupStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Integer> individualAndGroupStatistics(HttpServletRequest request) {

        Long medicalVisitorId = Long.parseLong(request.getParameter("medicalVisitorId"));
        Map<String, Integer> counts = new HashMap<>();
        counts.put("doctors", 0);
        counts.put("scheduledVisits", 0);
        counts.put("paidVisits", 0);
        counts.put("extraVisits", 0);
        counts.put("groupDoctors", 0);
        counts.put("groupScheduledVisits", 0);
        counts.put("groupPaidVisits", 0);
        counts.put("groupExtraVisits", 0);

        counts.put("isLeader", 0);

        //for individual visits
        List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.getUsersFromCurrentCycle(medicalVisitorId);

        for (ScheduledVisit sch : scheduledVisits) {
            counts.put("doctors", counts.get("doctors") + 1);
            counts.put("scheduledVisits", counts.get("scheduledVisits") + 1);
            counts.put("paidVisits", counts.get("paidVisits") + sch.getPaidVisits().size());
            counts.put("extraVisits", counts.get("extraVisits") + sch.getExtraVisits().size());
        }

        List<Group> leaders = groupRepository.findByLeader(userRepository.findOne(medicalVisitorId));

        if (!leaders.isEmpty()) {
            counts.put("isLeader", 1);
            List<ScheduledVisit> groupVisits = scheduledVisitRepository.findByMemberAndLeader(medicalVisitorId);

            for (ScheduledVisit sch : groupVisits) {
                counts.put("groupDoctors", counts.get("groupDoctors") + 1);
                counts.put("groupScheduledVisits", counts.get("groupScheduledVisits") + 1);
                counts.put("groupPaidVisits", counts.get("groupPaidVisits") + sch.getPaidVisits().size());
                counts.put("groupExtraVisits", counts.get("groupExtraVisits") + sch.getExtraVisits().size());
            }
        }

        return counts;
    }

}
