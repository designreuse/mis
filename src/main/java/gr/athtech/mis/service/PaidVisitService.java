package gr.athtech.mis.service;

import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.repository.PaidVisitRepository;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author JurgenPC
 */
@Service("paidVisitService")
public class PaidVisitService {
    
    private static final Logger logger = LoggerFactory.getLogger(PaidVisit.class);
    
    @Resource
    PaidVisitRepository repo;
    
    public List<PaidVisit> findAll() {
        List<PaidVisit> paidVisits = repo.findAll();

        logger.info("---------New Visits", paidVisits);
        return paidVisits;
    }
    
}
