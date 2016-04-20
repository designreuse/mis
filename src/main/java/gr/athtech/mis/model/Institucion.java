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
@Table(name="institucion")
public class Institucion implements Serializable{
    @Id
    @GeneratedValue
    private int institucionId;
    @Column
    private String institucionName;
    @OneToMany(targetEntity = Doctor.class, cascade = ALL)
    private List<Doctor> doctors;
    
    public Institucion(){}

    public Institucion(int institucionId, String institucionName, List<Doctor> doctors) {
        this.institucionId = institucionId;
        this.institucionName = institucionName;
        this.doctors = doctors;
    }

    public int getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(int institucionId) {
        this.institucionId = institucionId;
    }

    public String getInstitucionName() {
        return institucionName;
    }

    public void setInstitucionName(String institucionName) {
        this.institucionName = institucionName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
