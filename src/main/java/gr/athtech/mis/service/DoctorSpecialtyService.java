package gr.athtech.mis.service;

import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.repository.DoctorSpecialtyRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("doctorSpecialtyService")
public class DoctorSpecialtyService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorSpecialtyService.class);

    @Resource
    DoctorSpecialtyRepository repo;

    public List<DoctorSpecialty> findAll() {
        List<DoctorSpecialty> doctorSpecialties = repo.findAll();

        return doctorSpecialties;
    }
    
    
    public DoctorSpecialty findOne(Long id) {
        DoctorSpecialty doctorSpecialty = repo.findOne(id);

        return doctorSpecialty;
    }
}
