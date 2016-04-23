/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    @ManyToMany(mappedBy = "groups")
    private Set<ScheduledVisit> scheduledVisits;

    @OneToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    public Group() {
    }

    public Group(Long id, String name, Set<User> users, Set<ScheduledVisit> scheduledVisits, User leader) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Set<ScheduledVisit> getScheduledVisits() {
        return scheduledVisits;
    }

    public void setScheduledVisits(Set<ScheduledVisit> scheduledVisits) {
        this.scheduledVisits = scheduledVisits;
    }

}
