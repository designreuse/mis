package gr.athtech.mis.repository;

import gr.athtech.mis.model.PaidVisit;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author JurgenPC
 */
@Service("paidVisitRepository")
public class PaidVisitRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(PaidVisit.class);
    
    @Resource
    IPaidVisitRepository repo;
    
    public List<PaidVisit> findAll() {
        List<PaidVisit> paidVisits = repo.findAll();

        logger.info("---------New Visits", paidVisits);
        return paidVisits;
    }
    
    public PaidVisit save(PaidVisit pdvst){
        pdvst = repo.save(pdvst);
        return pdvst;
     }
    
    //public List<PaidVisit> getAllPaidVisits(List<ScheduledVisit> vst){
        //List<PaidVisit> allPaidVisits = repo.findByScheduledVisit(vst);
        
        //return allPaidVisits;
    //}
    
    public List<PaidVisit> getAllUserVisits(Long id)
    {
        List<PaidVisit> allUsersVisits = repo.findByScheduledVisitMedicalVisitorId(id);
        
        return allUsersVisits;
    }
    
    public List<PaidVisit> getAllUserVisitsByCurrentCycle(Long id)
    {
        List<PaidVisit> allUsersVisits = repo.findByScheduledVisitMedicalVisitorIdAndCurrentCycle(id);
        
        return allUsersVisits;
    }
    
     public List<PaidVisit> getAllGroupVisitsByCurrentCycle(Long id)
    {
        List<PaidVisit> allUsersVisits = repo.findByScheduledGroupIdAndCurrentCycle(id);
        
        return allUsersVisits;
    }
     
     public List<PaidVisit> findRelatedMembers(Long id)
    {
        List<PaidVisit> allMemberVisits = repo.findByScheduledGroupIdAndCurrentCycleForMembers(id);
        
        return allMemberVisits;
    } 
     
      public List<PaidVisit> findEitherMemberOrLeader(Long id)
    {
        List<PaidVisit> allKindsOfVisits = repo.findByBothLeaderAndMember(id);
        
        return allKindsOfVisits;
    } 
     
      
    public PaidVisit findById(Long id) {

        PaidVisit paidVisit = repo.findOne(id);

        return paidVisit;
    }
    
     /**
     * Delete a paid visit based on Id
     *
     * @param id
     */
    public void delete(Long id) {
        repo.delete(id);
    }
    
    public List<PaidVisit> getAllPaidVisitsByCycle(Long id){
        List<PaidVisit> allPaidVisits = repo.findByScheduledVisitCycleId(id);
        
        return allPaidVisits;
    }
    
    public List<PaidVisit> getAllPaidVisitsByCurrentCycle(){
        List<PaidVisit> allPaidVisits = repo.findByScheduledVisitCurrentCycle();
        
        return allPaidVisits;
    }
    
}
