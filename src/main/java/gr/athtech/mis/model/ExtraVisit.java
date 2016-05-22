package gr.athtech.mis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
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
 * @author xrist
 */
@Entity
@Table(name = "extra_visits")
public class ExtraVisit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "visit_date", nullable = false)
    private Date date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = true)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduled_visit_id")
    @JsonIgnore
    private ScheduledVisit scheduledVisit;

    public ExtraVisit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
