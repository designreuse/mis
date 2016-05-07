package gr.athtech.mis.repository;

import gr.athtech.mis.model.Institution;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("institutionRepository")
public class InstitutionRepository {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionRepository.class);

    @Resource
    IInstitutionRepository repo;

    public List<Institution> findAll() {
        List<Institution> institutions = repo.findAll();

        return institutions;
    }

    public Institution findOne(Long id) {
        Institution institution = repo.findOne(id);

        return institution;
    }
}
