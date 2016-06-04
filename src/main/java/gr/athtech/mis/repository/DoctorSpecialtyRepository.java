package gr.athtech.mis.repository;

import gr.athtech.mis.model.DoctorSpecialty;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Uses the methods of the IDoctorSpecialtyRepository interface
 * @author xrist
 */
@Service("doctorSpecialtyRepository")
public class DoctorSpecialtyRepository {

    private static final Logger logger = LoggerFactory.getLogger(DoctorSpecialtyRepository.class);

    @Resource
    IDoctorSpecialtyRepository repo;

    /**
     * Find all doctor specialties
     * 
     * @return List<DoctorSpecialty>
     */
    public List<DoctorSpecialty> findAll() {
        List<DoctorSpecialty> doctorSpecialties = repo.findAll();

        return doctorSpecialties;
    }
    
    /**
     * Find one specialty based on a given id
     * 
     * @param id
     * @return DoctorSpecialty
     */
    public DoctorSpecialty findOne(Long id) {
        DoctorSpecialty doctorSpecialty = repo.findOne(id);

        return doctorSpecialty;
    }
}
