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
    
}
