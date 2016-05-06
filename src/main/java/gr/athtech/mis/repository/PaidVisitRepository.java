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
import org.springframework.stereotype.Repository;

/**
 *
 * @author JurgenPC
 */
@Repository("paiddVisitRepository")
public interface PaidVisitRepository extends JpaRepository<PaidVisit, Long>{
    
    List<PaidVisit> findByScheduledVisit(List<ScheduledVisit> schVisit);
    
}
