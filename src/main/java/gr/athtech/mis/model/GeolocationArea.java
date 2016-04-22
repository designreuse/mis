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
 * @author jmone
 */
@Entity
public class GeolocationArea implements Serializable{
    @Id
    @GeneratedValue
    private int geoId;
    @Column(nullable = false)
    private String area;
    @OneToMany(mappedBy="doc_area")
    private List<Doctor> doctors;
    
    
    public GeolocationArea(){}

    public GeolocationArea(int geoId, String area, List<Doctor> doctors) {
        this.geoId = geoId;
        this.area = area;
        this.doctors = doctors;
    }

    public int getGeoId() {
        return geoId;
    }

    public void setGeoId(int geoId) {
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
