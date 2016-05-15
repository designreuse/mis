/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.List;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author it-support
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_members", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> members;

    @ManyToMany(mappedBy = "groups")
    private List<ScheduledVisit> scheduledVisits;

    @OneToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    public Group() {
    }

    public Group(Long id, String name, List<User> members, List<ScheduledVisit> scheduledVisits, User leader) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.scheduledVisits = scheduledVisits;
        this.leader = leader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<ScheduledVisit> getScheduledVisits() {
        return scheduledVisits;
    }

    public void setScheduledVisits(List<ScheduledVisit> scheduledVisits) {
        this.scheduledVisits = scheduledVisits;
    }

}
