/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Described the cities associated with a geolocation area
 * 
 * @author it-support
 */
@Entity
@Table(name = "cities")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geolocation_area_id")
    @JsonBackReference
    private GeolocationArea geolocationArea;

    public City() {
    }

    public City(Long id, String name, GeolocationArea geolocationArea) {
        this.id = id;
        this.name = name;
        this.geolocationArea = geolocationArea;
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

    public GeolocationArea getGeolocationArea() {
        return geolocationArea;
    }

    public void setGeolocationArea(GeolocationArea geolocationArea) {
        this.geolocationArea = geolocationArea;
    }

}
