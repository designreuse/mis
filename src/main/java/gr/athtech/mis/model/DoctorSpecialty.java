/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "doctorspecialty")
public class DoctorSpecialty {
    @Id
    @GeneratedValue
    private Long docspecId;
    @Column
    private String speciality;
    private final Long docspecid;

    public DoctorSpecialty(Long docspecId, String speciality) {
        this.docspecid = docspecId;
        this.speciality = speciality;
    }

    public Long getDocspecid() {
        return docspecid;
    }

    public void setDocspecid(Long docspecId) {
        this.docspecId = docspecId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
