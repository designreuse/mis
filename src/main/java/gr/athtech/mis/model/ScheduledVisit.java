/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jmone
 */
@Entity
public class ScheduledVisit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduledVisitId;
    @Column(nullable = false)
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="visitor_id", nullable = false)
    private User medicalVisitor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id", nullable = false)
    private Doctor visitingDoctor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cycle_id", nullable = false)
    private Cycle scheduledCycle;
    @OneToMany(mappedBy="scheduledVisit")
    private List<PaidVisit> paidVisits;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_visit", joinColumns = @JoinColumn(name = "scheduledVisit_id", referencedColumnName = "scheduledVisitId"), inverseJoinColumns = @JoinColumn(name = "groupVisit_id", referencedColumnName = "groupId"))
    private Set<Groups> groupVisits;
    
    

    public ScheduledVisit() {}

    public ScheduledVisit(int scheduledVisitId, String status, User medicalVisitor, Doctor visitingDoctor, Cycle scheduledCycle, List<PaidVisit> paidVisits, Set<Groups> groupVisits) {
        this.scheduledVisitId = scheduledVisitId;
        this.status = status;
        this.medicalVisitor = medicalVisitor;
        this.visitingDoctor = visitingDoctor;
        this.scheduledCycle = scheduledCycle;
        this.paidVisits = paidVisits;
        this.groupVisits = groupVisits;
    }

    public int getScheduledVisitId() {
        return scheduledVisitId;
    }

    public void setScheduledVisitId(int scheduledVisitId) {
        this.scheduledVisitId = scheduledVisitId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getMedicalVisitor() {
        return medicalVisitor;
    }

    public void setMedicalVisitor(User medicalVisitor) {
        this.medicalVisitor = medicalVisitor;
    }

    public Doctor getVisitingDoctor() {
        return visitingDoctor;
    }

    public void setVisitingDoctor(Doctor visitingDoctor) {
        this.visitingDoctor = visitingDoctor;
    }

    public Cycle getScheduledCycle() {
        return scheduledCycle;
    }

    public void setScheduledCycle(Cycle scheduledCycle) {
        this.scheduledCycle = scheduledCycle;
    }

    public List<PaidVisit> getPaidVisits() {
        return paidVisits;
    }

    public void setPaidVisits(List<PaidVisit> paidVisits) {
        this.paidVisits = paidVisits;
    }

    public Set<Groups> getGroupVisits() {
        return groupVisits;
    }

    public void setGroupVisits(Set<Groups> groupVisits) {
        this.groupVisits = groupVisits;
    }
       
}
