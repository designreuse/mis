package gr.athtech.mis.repository;

import gr.athtech.mis.model.Doctor;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service("doctorRepository")
@Transactional
public class DoctorRepository {

    private static final Logger logger = LoggerFactory.getLogger(DoctorRepository.class);

    @Resource
    IDoctorRepository repo;    

    /**
     * Return a list of all the doctors
     *
     * @return
     */
    public List<Doctor> findAll() {
        List<Doctor> doctors = repo.findAll();

        return doctors;
    }

    /**
     * Return a doctor based on a given id
     *
     * @param id
     * @return
     */
    public Doctor findOne(Long id) {
        Doctor doctor = repo.findOne(id);
        
        /*if(doctor!=null){
            doctor.getGeolocationArea();
            doctor.getCity();
            doctor.getInstitution();
            doctor.getSpecialty();
            doctor.getScheduledVisits();
        }
*/
        return doctor;
    }

    /**
     * Save a new doctor instance
     *
     * @param doctor
     * @return
     */
    public Doctor save(Doctor doctor) {
        doctor = repo.save(doctor);
        return doctor;
    }
    
     public Doctor update(Doctor doctor) {
        doctor = repo.save(doctor);
        return doctor;
    }
     
     public void delete(Long id){
         repo.delete(id);
     }

    public List<Doctor> getAvailableDoctorList() {
        List<Doctor> doctorList = repo.getAvailableDoctors();
        return doctorList;
    }
    
    public List<Doctor> findDoctorByCycleId(Long id){
        List<Doctor> doctorList = repo.getAvailableDoctorsList(id);
        
        return doctorList;
    }
    
    public List<Doctor> findByNameOrAddress(String firstName, String lastName, String address){
        return repo.findByFirstNameLikeAndLastNameLikeOrAddressLike(firstName, lastName, address);
    }
}
