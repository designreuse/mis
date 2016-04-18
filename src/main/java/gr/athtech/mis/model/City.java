
package gr.athtech.mis.model;

import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "city")
public class City {
    
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String cityname;

    @OneToMany(targetEntity = Doctor.class, cascade = ALL)
    private List<Doctor> doctors;
    
    public City(Long id, String cityname) {
        this.id = id;
        this.cityname = cityname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    
}
