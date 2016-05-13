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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author it-support
 */
@Entity
@Table(name = "paid_visits")
public class PaidVisit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String week;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String hour;

    @Column(nullable = false)
    private String isGroup;

    @Column(nullable = false)
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduled_visit_id", nullable = false)
    private ScheduledVisit scheduledVisit;

    public PaidVisit() {
    }

    public PaidVisit(Long id, String week, Date date, String hour, String isGroup, String comments, ScheduledVisit scheduledVisit) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.hour = hour;
        this.isGroup = isGroup;
        this.comments = comments;
        this.scheduledVisit = scheduledVisit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ScheduledVisit getScheduledVisit() {
        return scheduledVisit;
    }

    public void setScheduledVisit(ScheduledVisit scheduledVisit) {
        this.scheduledVisit = scheduledVisit;
    }

}
