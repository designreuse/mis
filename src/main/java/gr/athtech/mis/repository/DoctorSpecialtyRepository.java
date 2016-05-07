package gr.athtech.mis.repository;

import gr.athtech.mis.model.DoctorSpecialty;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("doctorSpecialtyRepository")
public class DoctorSpecialtyRepository {

    private static final Logger logger = LoggerFactory.getLogger(DoctorSpecialtyRepository.class);

    @Resource
    IDoctorSpecialtyRepository repo;

    public List<DoctorSpecialty> findAll() {
        List<DoctorSpecialty> doctorSpecialties = repo.findAll();

        return doctorSpecialties;
    }
    
    
    public DoctorSpecialty findOne(Long id) {
        DoctorSpecialty doctorSpecialty = repo.findOne(id);

        return doctorSpecialty;
    }
}
