/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author it-support
 */
@Entity
public class City implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String cityname;
    @OneToMany(mappedBy="doc_city")
    private List<Doctor> doctors;
    
    public City(){}

    public City(int id, String cityname, List<Doctor> doctors) {
        this.id = id;
        this.cityname = cityname;
        this.doctors = doctors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
