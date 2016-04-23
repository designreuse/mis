package gr.athtech.mis.service;

import gr.athtech.mis.model.Institution;
import gr.athtech.mis.repository.InstitutionRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("institutionService")
public class InstitutionService {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionService.class);

    @Resource
    InstitutionRepository repo;

    public List<Institution> findAll() {
        List<Institution> institutions = repo.findAll();

        return institutions;
    }

    public Institution findOne(Long id) {
        Institution institution = repo.findOne(id);

        return institution;
    }
}
