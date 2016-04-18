package gr.athtech.mis.model;

import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String rolename;
    
    @ManyToOne(targetEntity = Role.class, cascade = ALL)
    @JoinColumn(name="user_id")
    private User user;
    
    public Role(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
        if(!user.getRole().contains(this)){
            user.getRole().add(this);
        }
    } 
}
