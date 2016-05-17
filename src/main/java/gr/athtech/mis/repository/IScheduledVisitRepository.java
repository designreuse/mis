/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author it-support
 */
@Repository("iScheduledVisitRepository")
public interface IScheduledVisitRepository extends JpaRepository<ScheduledVisit, Long>{
    
    //List<ScheduledVisit> findByMedicalVisitor(User user);
 
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.medicalVisitors md "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE AND md.id = ?1")
    List<ScheduledVisit> findByMedicalVisitorId(Long id);

    
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND g.leader.id = ?1")
    List<ScheduledVisit> findByGroupId(Long id);
 
    @Query("SELECT s FROM ScheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE")
    public List<ScheduledVisit> findByCurrentCycle();
    
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND g.id = ?1")
    public List<ScheduledVisit> findByGroupNumber(Long id);
    
    public List<ScheduledVisit> findScheduledVisitByCycle(Cycle cycle);
      
}
