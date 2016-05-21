package gr.athtech.mis.web;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.repository.CityRepository;
import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.DoctorSpecialtyRepository;
import gr.athtech.mis.repository.GeolocationAreaRepository;
import gr.athtech.mis.repository.InstitutionRepository;
import gr.athtech.mis.repository.PaidVisitRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import gr.athtech.mis.service.AuthService;
import gr.athtech.mis.service.DoctorService;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

    private final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private DoctorService service;
    @Autowired
    private DoctorRepository repo;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private GeolocationAreaRepository geolocationAreaRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private DoctorSpecialtyRepository doctorSpecialtyRepository;
    @Autowired
    private PaidVisitRepository paidVisitRepository;
    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;

    /**
     * Return the view that will display all the doctors
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<Doctor> doctors = repo.findAll();
        List<City> cities = cityRepository.findAll();
        List<GeolocationArea> geolocationAreas = geolocationAreaRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        List<DoctorSpecialty> doctorSpecialties = doctorSpecialtyRepository.findAll();

        doctors = service.setPermissions(doctors);

        model.put("cities", cities);
        model.put("geolocationAreas", geolocationAreas);
        model.put("institutions", institutions);
        model.put("doctorSpecialties", doctorSpecialties);
        model.put("doctors", doctors);

        return "doctors/view";
    }

    /**
     * Show one doctor
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public String one(@PathVariable("id") Long id, Model model) {

        Doctor doctor = repo.findOne(id);
        doctor = service.setPermission(doctor);
        List<ScheduledVisit> newVisitsList = scheduledVisitRepository.showScheduledVisitsByDoctorId(id);
        List<PaidVisit> paidVisits = paidVisitRepository.getAllPaidVisitsByDoctorId(id);

        model.addAttribute("doctor", doctor);
        model.addAttribute("newVisitsList", newVisitsList);
        model.addAttribute("paidVisits", paidVisits);

        return "doctors/one";
    }

    /**
     * Return the view that holds the create new doctor form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        //fetch all the attributes that will be prefilled
        List<City> cities = cityRepository.findAll();
        List<GeolocationArea> geolocationAreas = geolocationAreaRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        List<DoctorSpecialty> doctorSpecialties = doctorSpecialtyRepository.findAll();

        model.addAttribute("cities", cities);
        model.addAttribute("geolocationAreas", geolocationAreas);
        model.addAttribute("institutions", institutions);
        model.addAttribute("doctorSpecialties", doctorSpecialties);

        return "doctors/create";
    }

    /**
     * Return the view that holds the edit doctor form
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {

        //fetch all the attributes that will be prefilled
        List<City> cities = cityRepository.findAll();
        List<GeolocationArea> geolocationAreas = geolocationAreaRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        List<DoctorSpecialty> doctorSpecialties = doctorSpecialtyRepository.findAll();

        Doctor doctor = repo.findOne(id);

        model.addAttribute("cities", cities);
        model.addAttribute("geolocationAreas", geolocationAreas);
        model.addAttribute("institutions", institutions);
        model.addAttribute("doctorSpecialties", doctorSpecialties);
        model.addAttribute("doctor", doctor);

        return "doctors/edit";
    }

    /**
     * Store a new doctor
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request) {

        Doctor doctor = new Doctor();
        doctor = service.getDataFromRequest(request, doctor);

        repo.save(doctor);

        //if the logged n user is a medical visitor, add a scheduleVisit
        if (authService.isMedicalVisitor()) {
            service.assignDoctorToUser(doctor);
        }

        return "redirect:/doctors/";
    }

    /**
     * Update an existing doctor
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(HttpServletRequest request) {

        Doctor doctor = repo.findOne(Long.parseLong(request.getParameter("id")));
        doctor = service.getDataFromRequest(request, doctor);

        repo.update(doctor);

        return "redirect:/doctors/";
    }

    /**
     * Delete a doctor, if s/he doesn't have paid visits
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id) {

        boolean flag = false;
        Doctor doctor = repo.findOne(id);
        for (ScheduledVisit scheduledVisit : doctor.getScheduledVisits()) {
            if ("Paid".equals(scheduledVisit.getStatus())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            repo.delete(id);
        }

        return flag;
    }

    @RequestMapping(value = "/byCycle/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Doctor> show(@PathVariable("id") Long id
    ) {

        List<Doctor> doctorList = repo.findDoctorByCycleId(id);

        return doctorList;
    }

    @RequestMapping(value = "/checkUnique", method = RequestMethod.GET)
    @ResponseBody
    public List<Doctor> checkUnique(HttpServletRequest request
    ) {

        List<Doctor> doctors = repo.findByNameOrAddress(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("address"));

        if (request.getParameter("id") != null) {
            for (Doctor doctor : doctors) {
                if (doctor.getId() == Long.parseLong(request.getParameter("id")) && doctors.size() == 1) {
                    doctors = new ArrayList<>();
                    break;
                }
            }
        }

        return doctors;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Doctor> search(HttpServletRequest request
    ) {

        City city = null;
        GeolocationArea geolocationArea = null;
        Institution institution = null;
        DoctorSpecialty doctorSpecialty = null;

        if (!request.getParameter("cityId").isEmpty()) {
            city = cityRepository.findOne(Long.parseLong(request.getParameter("cityId")));
        }
        if (!request.getParameter("geolocationAreaId").isEmpty()) {
            geolocationArea = geolocationAreaRepository.findOne(Long.parseLong(request.getParameter("geolocationAreaId")));
        }
        if (!request.getParameter("institutionId").isEmpty()) {
            institution = institutionRepository.findOne(Long.parseLong(request.getParameter("institutionId")));
        }
        if (!request.getParameter("specialtyId").isEmpty()) {
            doctorSpecialty = doctorSpecialtyRepository.findOne(Long.parseLong(request.getParameter("specialtyId")));
        }

        List<Doctor> doctors = repo.search(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("address"),
                request.getParameter("phone"),
                request.getParameter("position"),
                request.getParameter("email"),
                city, geolocationArea, institution, doctorSpecialty);

        doctors = service.setPermissions(doctors);
        return doctors;
    }
}
