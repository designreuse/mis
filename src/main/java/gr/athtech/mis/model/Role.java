/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author it-support
 */
@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    //Used in order to create jointed tables for many to many relationships
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @Transient
    private String publicName;

    public Role() {
    }

    public Role(Long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public String getPublicName() {
        String publicName = "";
        if ("ROLE_ADMIN".equals(this.name)) {
            publicName = "Admin";
        } else if ("ROLE_MEDICAL_VISITOR".equals(this.name)) {
            publicName = "Medical Visitor";
        }
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

}
