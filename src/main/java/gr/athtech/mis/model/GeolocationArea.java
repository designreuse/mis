/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    
    
    public GeolocationArea(){}

    public GeolocationArea(int geoId, String area) {
        this.geoId = geoId;
        this.area = area;
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

}
