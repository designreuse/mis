package gr.athtech.mis.service;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import gr.athtech.mis.repository.CityRepository;
import gr.athtech.mis.repository.DoctorSpecialtyRepository;
import gr.athtech.mis.repository.GeolocationAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import gr.athtech.mis.repository.InstitutionRepository;

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
}
