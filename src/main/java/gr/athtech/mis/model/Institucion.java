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

    public Institucion(int institucionId, String institucionName) {
        this.institucionId = institucionId;
        this.institucionName = institucionName;
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
    
    
    
    
}
