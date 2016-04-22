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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jmone
 */
@Entity
public class Cycle implements Serializable{
    
    @Id
    @GeneratedValue
    private int idcycle;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @OneToMany(mappedBy="scheduledCycle")
    private List<ScheduledVisit> schVisits;
    
    public Cycle(){}
    
    public Cycle(int idcycle, Date startDate, Date endDate, List<ScheduledVisit> schVisits) {
        this.idcycle = idcycle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schVisits = schVisits;
    }
    

    public int getIdcycle() {
        return idcycle;
    }

    public void setIdcycle(int idcycle) {
        this.idcycle = idcycle;
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

    public List<ScheduledVisit> getSchVisits() {
        return schVisits;
    }

    public void setSchVisits(List<ScheduledVisit> schVisits) {
        this.schVisits = schVisits;
    }
    
}
