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
public class DoctorSpecialty implements Serializable{
    @Id
    @GeneratedValue
    private int docspecId;
    @Column(nullable = false)
    private String speciality;
    
    
    public DoctorSpecialty(){}

    public DoctorSpecialty(int docspecId, String speciality) {
        this.docspecId = docspecId;
        this.speciality = speciality;
    }

    public int getDocspecId() {
        return docspecId;
    }

    public void setDocspecId(int docspecId) {
        this.docspecId = docspecId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
