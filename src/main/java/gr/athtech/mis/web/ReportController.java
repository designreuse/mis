package gr.athtech.mis.web;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.CycleRepository;
import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.GroupRepository;
import gr.athtech.mis.repository.PaidVisitRepository;
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
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PaidVisitRepository paidVisitRepository;
    
    //Individual statistics
    private float percentage;
    private float percentageFirst;
    private int totalCount;
    
    
    //Group statistics
    private float groupPercentage;
    private float groupPercentageFirst;
    private int groupTotalCount;


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
    
    //###REPORT 2 REDONE - JMONE
    @RequestMapping(value = "/allCycles", method = RequestMethod.GET)
    public String showUserByCycles(Map<String, Object> model) {

        List<User> userList = userRepository.findAll();
        List<Cycle> cyclesList = cycleRepository.findAll();
        model.put("userList", userList);
        model.put("cyclesList", cyclesList);
        return "reports/report2";

    }
    
    /**
     * Display the scheduled visits of the selected cycle
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/byCycle", method = RequestMethod.POST)
    public String showSelectedUserVisits(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {

        Long CycleId = Long.parseLong(request.getParameter("cycleId"));
        Long UserId = Long.parseLong(request.getParameter("userId"));
        
        //for the dropdown
        List<Cycle> cyclesList = cycleRepository.findAll();
        List<User> userList = userRepository.findAll();
        List<Doctor> doctorList = doctorRepository.findAll();
             
        //Overall visits by count - Individual Report
        User user = userRepository.findOne(UserId);
        List<ScheduledVisit> newVisits = scheduledVisitRepository.showVisitsByCycleAndUserId(UserId, CycleId);
        List<PaidVisit> FirstPaidVisits = paidVisitRepository.findTotalFirstVisitCount(UserId, CycleId);    
        List<PaidVisit> SecondPaidVisits = paidVisitRepository.findTotalSecondVisitCount(UserId, CycleId);      
        List<PaidVisit> ExtraPaidVisits = paidVisitRepository.findTotalExtraVisitCount(UserId, CycleId);
        percentage = (float) ((newVisits.size() * 100)/doctorList.size());
        percentageFirst = (float) ((FirstPaidVisits.size() * 100)/doctorList.size());
        totalCount = FirstPaidVisits.size() + SecondPaidVisits.size() + ExtraPaidVisits.size();
        
        //Overall visits by count - Group report       
            List<Group> userGroups = groupRepository.findByLeaderId(UserId);           
            List<ScheduledVisit> newGroupVisits = scheduledVisitRepository.showGroupVisitsByCycleAndUserId(UserId, CycleId);
            List<PaidVisit> firstGroupVisits = paidVisitRepository.findTotalGroupFirstVisitCount(UserId, CycleId);
            List<PaidVisit> secondGroupVisits = paidVisitRepository.findTotalGroupSecondVisitCount(UserId, CycleId);
            List<PaidVisit> extraGroupVisits = paidVisitRepository.findTotalGroupExtraVisitCount(UserId, CycleId);
            groupPercentage = (float) ((newGroupVisits.size() * 100)/doctorList.size());
            groupPercentageFirst = (float) ((firstGroupVisits.size() * 100)/doctorList.size());
            groupTotalCount = firstGroupVisits.size() + secondGroupVisits.size() + extraGroupVisits.size();
            
            model.put("userGroups", userGroups);
            model.put("newGroupVisits", newGroupVisits);
            model.put("firstGroupVisits", firstGroupVisits);
            model.put("secondGroupVisits", secondGroupVisits);
            model.put("extraGroupVisits", extraGroupVisits);
            model.put("groupTotalCount", groupTotalCount);
            model.put("groupPercentage", groupPercentage);
            model.put("groupPercentageFirst", groupPercentageFirst);
       
        
        //for the dropdown
        model.put("cyclesList", cyclesList);
        model.put("userList", userList);
        
        //for the individual report
        model.put("newVisits", newVisits);
        model.put("user", user);
        model.put("percentage", percentage);
        model.put("percentageFirst", percentageFirst);
        model.put("totalCount", totalCount);
        model.put("FirstPaidVisits", FirstPaidVisits);
        model.put("SecondPaidVisits", SecondPaidVisits);
        model.put("ExtraPaidVisits", ExtraPaidVisits);
        
        return "reports/report2";
    }

}
