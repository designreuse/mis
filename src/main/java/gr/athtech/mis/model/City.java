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
 * @author it-support
 */
@Entity
public class City implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String cityname;
    
    public City(){}

    public City(int id, String cityname) {
        this.id = id;
        this.cityname = cityname;
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

}
