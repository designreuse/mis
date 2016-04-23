/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "cycles")
public class Cycle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="start_date", nullable = false)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @OneToMany(mappedBy = "cycle")
    private List<ScheduledVisit> scheduledVisits;

    public Cycle() {
    }

    public Cycle(Long id, Date startDate, Date endDate, List<ScheduledVisit> scheduledVisits) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduledVisits = scheduledVisits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ScheduledVisit> getScheduledVisits() {
        return scheduledVisits;
    }

    public void setScheduledVisits(List<ScheduledVisit> scheduledVisits) {
        this.scheduledVisits = scheduledVisits;
    }

}
