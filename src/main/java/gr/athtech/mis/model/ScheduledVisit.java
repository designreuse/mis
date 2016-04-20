/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "scheduledvisit")
public class ScheduledVisit implements Serializable{
    @Id
    @GeneratedValue
    private Long IdVisit;
    @Column
    private String visitStatus;
    @ManyToOne(targetEntity = Role.class, cascade = ALL)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(targetEntity = Role.class, cascade = ALL)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @ManyToOne(targetEntity = Role.class, cascade = ALL)
    @JoinColumn(name="cycle_idcycle")
    private Cycle cycle;

    public ScheduledVisit(Long IdVisit, String visitStatus, User user, Doctor doctor, Cycle cycle) {
        this.IdVisit = IdVisit;
        this.visitStatus = visitStatus;
        this.user = user;
        this.doctor = doctor;
        this.cycle = cycle;
    }

    public Long getIdVisit() {
        return IdVisit;
    }

    public void setIdVisit(Long IdVisit) {
        this.IdVisit = IdVisit;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
    
}
