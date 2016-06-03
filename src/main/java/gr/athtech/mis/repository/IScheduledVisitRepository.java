/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.model.Role;
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
public interface IScheduledVisitRepository extends JpaRepository<ScheduledVisit, Long> {

    List<ScheduledVisit> findByMedicalVisitors(User user);

    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.medicalVisitors md "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE AND md.id = ?1")
    List<ScheduledVisit> findByMedicalVisitorId(Long id);

    //User is a group leader
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND g.leader.id = ?1")
    List<ScheduledVisit> findByGroupId(Long id);

    //User is a leader and also a member in different groups
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "JOIN g.members m "
            + "WHERE m.id = ?1 OR g.leader.id = ?1 "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND s.cycle.startDate <= CURRENT_DATE")
    public List<ScheduledVisit> findByGroupLeaderAndId(Long id);

    @Query("SELECT s FROM ScheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE")
    public List<ScheduledVisit> findByCurrentCycle();

    public List<ScheduledVisit> findScheduledVisitByCycle(Cycle cycle);

    //Scheduled Visits for the doctor info
    @Query("SELECT s FROM ScheduledVisit as s "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND s.doctor.id = ?1")
    public List<ScheduledVisit> findScheduledVisitsByDoctor(Long id);

    //User is a member of a group
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "JOIN g.members m "
            + "WHERE s.cycle.startDate <= CURRENT_DATE "
            + "AND s.cycle.endDate >= CURRENT_DATE "
            + "AND m.id = ?1")
    public List<ScheduledVisit> findByMemberID(Long id);
    
    //REPORTS------------------------------------------------------------------
    
    //Report 2 - Individual
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.medicalVisitors md WHERE md.id = ?1 "
            + "AND s.cycle.id = ?2")
    public List<ScheduledVisit> findScheduledVisitByUserAndCycle(Long userId, Long cycleId);
    
    //Report 2 - Group
    @Query("SELECT s FROM ScheduledVisit as s "
            + "JOIN s.groups g "
            + "WHERE g.leader.id = ?1 "
            + "AND s.cycle.id = ?2")
    public List<ScheduledVisit> findScheduledVisitByGroupAndCycle(Long userId, Long cycleId);

}
