package gr.athtech.mis.service;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.repository.CityRepository;
import gr.athtech.mis.repository.CycleRepository;
import gr.athtech.mis.repository.DoctorSpecialtyRepository;
import gr.athtech.mis.repository.GeolocationAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import gr.athtech.mis.repository.InstitutionRepository;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import gr.athtech.mis.repository.UserRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;

@Repository("doctorService")
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private GeolocationAreaRepository geolocationAreaRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private DoctorSpecialtyRepository doctorSpecialtyRepository;
    @Autowired
    private AuthService authService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ScheduledVisitRepository scheduledVisitRepository;
    @Resource
    private CycleRepository cycleRepository;

    /**
     * Create a doctor obj from request data
     *
     * @param request
     * @return
     */
    public Doctor getDataFromRequest(HttpServletRequest request, Doctor doctor) {
        City city = cityRepository.findOne(Long.parseLong(request.getParameter("cityId")));
        GeolocationArea geolocationArea = geolocationAreaRepository.findOne(Long.parseLong(request.getParameter("geolocationAreaId")));
        Institution institution = institutionRepository.findOne(Long.parseLong(request.getParameter("institutionId")));
        DoctorSpecialty doctorSpecialty = doctorSpecialtyRepository.findOne(Long.parseLong(request.getParameter("specialtyId")));

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

        return doctor;
    }

    /**
     * When a medical visitor creates a new user, create a new scheduled visit
     * and assign both the doctor and the medical user to it.
     * The scheduled visit is assigned to the current cycle,
     * that is the cycle that runs now.
     *
     * @param doctor
     * @return
     */
    public Long assignDoctorToUser(Doctor doctor) throws ParseException {

        ScheduledVisit scheduledVisit = new ScheduledVisit();
        scheduledVisit.setCycle(cycleRepository.getCurrentCycle());
        scheduledVisit.setMedicalVisitors(new ArrayList<>(Arrays.asList(userRepository.findOne(authService.getId()))));
        scheduledVisit.setDoctor(doctor);
        scheduledVisit.setStatus("Pending");

        scheduledVisitRepository.save(scheduledVisit);
        return scheduledVisit.getId();
    }

    /**
     * For a given list of doctors, check if the logged in user may edit them
     * (used in search, where we cannot use spring security, only JavaScript)
     *
     * @param doctors
     * @return
     */
    public List<Doctor> setPermissions(List<Doctor> doctors) {

        for (Doctor doctor : doctors) {
            setPermission(doctor);
        }

        return doctors;
    }

    /**
     * Set permissions for one doctor
     *
     * @param doctor
     * @return
     */
    public Doctor setPermission(Doctor doctor) {
        boolean isAdmin = authService.isAdmin();
        if (isAdmin) {
            doctor.setIsEditable(true);
            doctor.setIsDeletable(true);
        } else if (authService.canEditDoctor(doctor.getId())) {
            doctor.setIsEditable(true);
            doctor.setIsDeletable(false);
        } else {
            doctor.setIsEditable(false);
            doctor.setIsDeletable(false);
        }

        return doctor;
    }

}
