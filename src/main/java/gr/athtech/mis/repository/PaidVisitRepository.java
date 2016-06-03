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
    
    //Doctor's view of paid visits
    public List<PaidVisit> getAllPaidVisitsByDoctorId(Long id){
        List<PaidVisit> doctorsPaidVisits = repo.findVisitsByDoctorId(id);
        
        return doctorsPaidVisits;
    }
        
    public List<PaidVisit> findRelatedMembersId(Long id)
    {
        List<PaidVisit> allMemberVisits = repo.findByMemberId(id);
        
        return allMemberVisits;
    } 
    
    //REPORTS-----------------------------------------------------------------
    
    //Individual Visits
    
    public List<PaidVisit> findTotalCount(Long id){
        List<PaidVisit> listCount = repo.countPaidVisits(id);
        
        return listCount;
    }
    
    //First visits
    public List<PaidVisit> findTotalFirstVisitCount(Long uId, Long cId){
        List<PaidVisit> FirstListCount = repo.countFirstPaidVisits(uId, cId);
        
        return FirstListCount;
    }
    
    //Second visits
    public List<PaidVisit> findTotalSecondVisitCount(Long uId, Long cId){
        List<PaidVisit> SecondListCount = repo.countSecondPaidVisits(uId, cId);
        
        return SecondListCount;
    }
    
    //Extra visits
    public List<PaidVisit> findTotalExtraVisitCount(Long uId, Long cId){
        List<PaidVisit> ExtraListCount = repo.countExtraPaidVisits(uId, cId);
        
        return ExtraListCount;
    }
    
    //Group Visits
    
    //First visits
    public List<PaidVisit> findTotalGroupFirstVisitCount(Long uId, Long cId){
        List<PaidVisit> FirstGroupListCount = repo.countFirstGroupPaidVisits(uId, cId);
        
        return FirstGroupListCount;
    }
    
    //Second visits
    public List<PaidVisit> findTotalGroupSecondVisitCount(Long uId, Long cId){
        List<PaidVisit> SecondGroupListCount = repo.countSecondGroupPaidVisits(uId, cId);
        
        return SecondGroupListCount;
    }
    
    //Extra visits
    public List<PaidVisit> findTotalGroupExtraVisitCount(Long uId, Long cId){
        List<PaidVisit> ExtraGroupListCount = repo.countExtraGroupPaidVisits(uId, cId);
        
        return ExtraGroupListCount;
    }
    
}
