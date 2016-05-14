package gr.athtech.mis.web;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import gr.athtech.mis.repository.CityRepository;
import gr.athtech.mis.repository.DoctorRepository;
import gr.athtech.mis.repository.DoctorSpecialtyRepository;
import gr.athtech.mis.repository.GeolocationAreaRepository;
import gr.athtech.mis.repository.InstitutionRepository;
import gr.athtech.mis.service.DoctorService;

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
    private DoctorService doctorService;
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

    /**
     * Return the view that will display all the doctors
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        List<Doctor> doctors = repo.findAll();

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

        model.addAttribute("doctor", doctor);

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
        doctor = doctorService.getDataFromRequest(request, doctor);

        repo.save(doctor);

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
        doctor = doctorService.getDataFromRequest(request, doctor);

        repo.update(doctor);

        return "redirect:/doctors/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {

        repo.delete(id);
        return "redirect:/doctors/";
    }
       
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Doctor> show(@PathVariable("id") Long id) {
        
        List<Doctor> doctorList =repo.findDoctorByCycleId(id);
     
        return doctorList;
    }

}
