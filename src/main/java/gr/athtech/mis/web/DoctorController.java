package gr.athtech.mis.web;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.service.CityService;
import gr.athtech.mis.service.DoctorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
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

    /**
     * Return the view that will display all the doctors
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<Doctor> doctors = doctorService.findAll();

        model.addAttribute("doctors", doctors);

        return "doctors";
    }

    /**
     * Return the view that holds the create new doctor form
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String store(Model model) {

        //fetch all the attributed that wil be prefilled
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);

        return "doctors.create";
    }

    /**
     * Return the view that holds the edit doctor form
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {

        Doctor doctor = doctorService.findOne(id);
        model.addAttribute("doctor", doctor);

        return "person";
    }

    /**
     * Store a new doctor
     *
     * @param doctor
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@ModelAttribute("doctor") Doctor doctor) {

        //doctorService.store(doctor);   
        return "doctors";
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
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "application/json",headers="Accept=application/json")
    public List<Doctor> index() {

        List<Doctor> doctors = doctorService.findAll();

        return doctors;
    }
}
