/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author it-support
 */
@Entity
public class Groups implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;
    @ManyToMany(mappedBy="groups")
    private Set<User> users;
    @OneToOne
    @JoinColumn(name = "leaderId", nullable = false)
    private User leader;

    public Groups() {
    }

    public Groups(int groupId, Set<User> users, User leader) {
        this.groupId = groupId;
        this.users = users;
        this.leader = leader;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
     
}
