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
@Table(name = "doctorspecialty")
public class DoctorSpecialty implements Serializable{
    @Id
    @GeneratedValue
    private Long docspecId;
    @Column
    private String speciality;
    @OneToMany(targetEntity = Doctor.class, cascade = ALL)
    private List<Doctor> doctors;
    
    
    public DoctorSpecialty(){}

    public DoctorSpecialty(Long docspecId, String speciality, List<Doctor> doctors) {
        this.docspecId = docspecId;
        this.speciality = speciality;
        this.doctors = doctors;
    }

    public Long getDocspecId() {
        return docspecId;
    }

    public void setDocspecId(Long docspecId) {
        this.docspecId = docspecId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    
    
    
    
}
