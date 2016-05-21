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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "scheduled_visits")
public class ScheduledVisit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name = "individual_visits", joinColumns = @JoinColumn(name = "scheduled_visit_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicalVisitor_id", referencedColumnName = "id"))
    private List<User> medicalVisitors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cycle_id", nullable = false)
    private Cycle cycle;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scheduledVisit")
    private List<PaidVisit> paidVisits;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "groups_visits", joinColumns = @JoinColumn(name = "visit_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private List<Group> groups;

    public ScheduledVisit() {
    }

    public ScheduledVisit(Long id, String status, List<User> medicalVisitors, Doctor doctor, Cycle cycle, List<PaidVisit> paidVisits, List<Group> groups) {
        this.id = id;
        this.status = status;
        this.medicalVisitors = medicalVisitors;
        this.doctor = doctor;
        this.cycle = cycle;
        this.paidVisits = paidVisits;
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getMedicalVisitors() {
        return medicalVisitors;
    }

    public void setMedicalVisitors(List<User> medicalVisitors) {
        this.medicalVisitors = medicalVisitors;
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

    public List<PaidVisit> getPaidVisits() {
        return paidVisits;
    }

    public void setPaidVisits(List<PaidVisit> paidVisits) {
        this.paidVisits = paidVisits;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
