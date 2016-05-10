/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.PaidVisit;
import gr.athtech.mis.model.ScheduledVisit;
import java.util.List;
import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JurgenPC
 */
@Repository("iPaidVisitRepository")
public interface IPaidVisitRepository extends JpaRepository<PaidVisit, Long>{
    
    List<PaidVisit> findByScheduledVisit(List<ScheduledVisit> schVisit);
    
    /**
     *
     * @param id
     * @return
     */
    @Query("SELECT v FROM PaidVisit as v "
            + "JOIN v.scheduledVisit as s "
            + "WHERE s.medicalVisitor.id = ?1")
    public List<PaidVisit> findByScheduledVisitMedicalVisitorId(Long id);
    
}
