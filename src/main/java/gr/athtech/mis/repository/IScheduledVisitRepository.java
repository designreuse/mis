/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

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
    
    List<ScheduledVisit> findByMedicalVisitor(User user);
    
    @Query("SELECT s FROM ScheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE AND s.medicalVisitor.id = ?1")
    List<ScheduledVisit> findByMedicalVisitorId(Long id);
    
    @Query("SELECT s FROM ScheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE")
    public List<ScheduledVisit> findByCurrentCycle();
    
}
