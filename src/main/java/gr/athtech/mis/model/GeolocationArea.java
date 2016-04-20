/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
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
@Table(name="geolocationarea")
public class GeolocationArea implements Serializable{
    @Id
    @GeneratedValue
    private Long geoId;
    @Column
    private String area;
    @OneToMany(targetEntity = Doctor.class, cascade = ALL)
    private List<Doctor> doctors;
    
    public GeolocationArea(){}

    public GeolocationArea(Long geoId, String area, List<Doctor> doctors) {
        this.geoId = geoId;
        this.area = area;
        this.doctors = doctors;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    } 
}
