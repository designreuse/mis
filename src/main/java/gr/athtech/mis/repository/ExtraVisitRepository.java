package gr.athtech.mis.repository;

import gr.athtech.mis.model.ExtraVisit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("extraVisitRepository")
public class ExtraVisitRepository {

    private static final Logger logger = LoggerFactory.getLogger(ExtraVisitRepository.class);

    @Resource
    IExtraVisitRepository repo;

    public ExtraVisit save(ExtraVisit extraVisit){
        return repo.save(extraVisit);
    }
}
