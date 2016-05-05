/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.service;

import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.repository.ScheduledVisitRepository;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmone
 */
@Service("scheduledVisitService")
public class ScheduledVisitService {
    
    private static final Logger logger = LoggerFactory.getLogger(ScheduledVisit.class);
    
    @Resource
    ScheduledVisitRepository repo;
    
    public List<ScheduledVisit> findAll() {
        List<ScheduledVisit> newVisits = repo.findAll();

        logger.info("---------New Visits", newVisits);
        return newVisits;
    }
    
    public ScheduledVisit save(ScheduledVisit schvst){
        schvst = repo.save(schvst);
        return schvst;
     }
    
    
    /**
     * Delete a scheduled visit based on Id
     *
     * @param id
     */
    public void delete(Long id) {
        repo.delete(id);
    }
    
}
