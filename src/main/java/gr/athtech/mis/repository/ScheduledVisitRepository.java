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
import org.springframework.stereotype.Repository;

/**
 *
 * @author it-support
 */
@Repository("scheduledVisitRepository")
public interface ScheduledVisitRepository extends JpaRepository<ScheduledVisit, Long>{
    
    List<ScheduledVisit> findByMedicalVisitor(User user);
    
}
