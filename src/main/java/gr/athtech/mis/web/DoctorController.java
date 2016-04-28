package gr.athtech.mis.web;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import gr.athtech.mis.service.CityService;
import gr.athtech.mis.service.DoctorService;
import gr.athtech.mis.service.DoctorSpecialtyService;
import gr.athtech.mis.service.GeolocationAreaService;
import gr.athtech.mis.service.InstitutionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

    private final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CityService cityService;
    @Autowired
    private GeolocationAreaService geolocationAreaService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DoctorSpecialtyService doctorSpecialtyService;

    /**
     * Return the view that will display all the doctors
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<Doctor> doctors = doctorService.findAll();

        logger.debug("------------------DOCTORS");
        model.put("doctors", doctors);

        return "doctors/view";
    }

    /**
     * Return the view that holds the create new doctor form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        //fetch all the attributed that wil be prefilled
        List<City> cities = cityService.findAll();
        List<GeolocationArea> geolocationAreas = geolocationAreaService.findAll();
        List<Institution> institutions = institutionService.findAll();
        List<DoctorSpecialty> doctorSpecialties = doctorSpecialtyService.findAll();

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

        Doctor doctor = doctorService.findOne(id);
        model.addAttribute("doctor", doctor);

        return "doctors/edit";
    }

    /**
     * Store a new doctor
     *
     * @param doctor
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(HttpServletRequest request,
            HttpServletResponse response, Model model) {

        City city = cityService.findOne(Long.parseLong(request.getParameter("cityId")));
        GeolocationArea geolocationArea = geolocationAreaService.findOne(Long.parseLong(request.getParameter("geolocationAreaId")));        
        Institution institution = institutionService.findOne(Long.parseLong(request.getParameter("institutionId")));    
        DoctorSpecialty doctorSpecialty = doctorSpecialtyService.findOne(Long.parseLong("1"));    
       
        Doctor doctor = new Doctor();
        doctor.setFirstName(request.getParameter("firstName"));
        doctor.setLastName(request.getParameter("lastName"));
        doctor.setAddress(request.getParameter("address"));
        doctor.setEmail(request.getParameter("email"));
        doctor.setPhone(request.getParameter("phone"));
        doctor.setPosition(request.getParameter("position"));
        doctor.setCity(city);
        doctor.setGeolocationArea(geolocationArea);
        doctor.setInstitution(institution);
        doctor.setSpecialty(doctorSpecialty);

        logger.debug("----- New doctor: ", doctor);

        doctorService.save(doctor);
        
        return "doctors/view";
    }

    /**
     * Update an existing doctor
     *
     * @param doctor
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute("doctor") Doctor doctor) {

        //doctorService.update(doctor);     
        return "doctors";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {

        //doctorService.delete(id);
        return "doctors";
    }

    /**
     * Get all the doctors and return them in json format
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public List<Doctor> index() {

        List<Doctor> doctors = doctorService.findAll();

        return doctors;
    }
}
