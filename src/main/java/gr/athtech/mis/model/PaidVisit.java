/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author it-support
 */
@Entity
public class PaidVisit implements Serializable{
    @Id
    @GeneratedValue
    private int idPaidVisit;
    @Column(nullable = false)
    private String week;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String hour;
    @Column(nullable = false)
    private String isGroup;
    @Column(nullable = false)
    private String comment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="scheduledVisitId", nullable = false)
    private ScheduledVisit scheduledVisit;
    
    public PaidVisit(){}

    public PaidVisit(int idPaidVisit, String week, Date date, String hour, String isGroup, String comment, ScheduledVisit scheduledVisit) {
        this.idPaidVisit = idPaidVisit;
        this.week = week;
        this.date = date;
        this.hour = hour;
        this.isGroup = isGroup;
        this.comment = comment;
        this.scheduledVisit = scheduledVisit;
    }

    public int getIdPaidVisit() {
        return idPaidVisit;
    }

    public void setIdPaidVisit(int idPaidVisit) {
        this.idPaidVisit = idPaidVisit;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ScheduledVisit getScheduledVisit() {
        return scheduledVisit;
    }

    public void setScheduledVisit(ScheduledVisit scheduledVisit) {
        this.scheduledVisit = scheduledVisit;
    }
    
}
