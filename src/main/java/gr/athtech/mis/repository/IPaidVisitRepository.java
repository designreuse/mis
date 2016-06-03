/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JurgenPC
 */
@Repository("iPaidVisitRepository")
public interface IPaidVisitRepository extends JpaRepository<PaidVisit, Long>{
    
    /**
     * 
     * @param schVisit
     * @return 
     */
    @Query()
    List<PaidVisit> findByScheduledVisit(List<ScheduledVisit> schVisit);
    
    /**
     *
     * @param id
     * @return
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.medicalVisitors as md "
            + "WHERE md.id = ?1")
    public List<PaidVisit> findByScheduledVisitMedicalVisitorId(Long id);
    
    /**
     * @param id
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.medicalVisitors as md "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE AND md.id = ?1")
    public List<PaidVisit> findByScheduledVisitMedicalVisitorIdAndCurrentCycle(Long id);
    
     /**
     * @param id
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE AND g.leader.id = ?1")
    public List<PaidVisit> findByScheduledGroupIdAndCurrentCycle(Long id);
    
    /**
     * @param id
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "JOIN g.members as m "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND g.leader.id = ?1 "
            + "OR m.id = ?1")
    public List<PaidVisit> findByBothLeaderAndMember(Long id);
    
    /**
     * 
     * @param id
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "WHERE s.cycle.id = ?1")
    public List<PaidVisit> findByScheduledVisitCycleId(Long id);
    
    /**
     * 
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE")
    public List<PaidVisit> findByScheduledVisitCurrentCycle();
    
    
    //Doctor's view of paid visits
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND s.doctor.id = ?1")
    public List<PaidVisit> findVisitsByDoctorId(Long id);
    
    //User is a member of a group
    /**
     * @param id
     * @return 
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "JOIN g.members as m "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND m.id = ?1")
    public List<PaidVisit> findByMemberId(Long id);
    
    
    //REPORTS--------------------------------------------------------------
    //INDIVIDUAL VISITS
    
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "WHERE s.id = ?1 "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND s.cycle.startDate <= CURRENT_DATE")
    public List<PaidVisit> countPaidVisits(Long id);
      
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.medicalVisitors as md "
            + "WHERE md.id = ?1 "
            + "AND s.cycle.id = ?2 "
            + "AND v.visitCount = '1'")
    public List<PaidVisit> countFirstPaidVisits(Long userId, Long cycleId);
    
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.medicalVisitors as md "
            + "WHERE v.visitCount = '2' "
            + "AND md.id = ?1 "
            + "AND s.cycle.id = ?2")
    public List<PaidVisit> countSecondPaidVisits(Long userId, Long cycleId);
    
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.medicalVisitors as md "
            + "WHERE md.id = ?1 "
            + "AND s.cycle.id = ?2 "
            + "AND v.visitCount = 'extra'")
    public List<PaidVisit> countExtraPaidVisits(Long userId, Long cycleId);
    
    
    //GROUP VISITS
      
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "WHERE g.leader.id = ?1 "
            + "AND s.cycle.id = ?2 "
            + "AND v.visitCount = '1'")
    public List<PaidVisit> countFirstGroupPaidVisits(Long userId, Long cycleId);
    
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "WHERE v.visitCount = '2' "
            + "AND g.leader.id = ?1 "
            + "AND s.cycle.id = ?2")
    public List<PaidVisit> countSecondGroupPaidVisits(Long userId, Long cycleId);
    
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "JOIN s.groups as g "
            + "WHERE g.leader.id = ?1 "
            + "AND s.cycle.id = ?2 "
            + "AND v.visitCount = 'extra'")
    public List<PaidVisit> countExtraGroupPaidVisits(Long userId, Long cycleId);
    
}
