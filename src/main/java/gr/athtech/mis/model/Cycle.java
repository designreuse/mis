/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jmone
 */
@Entity
public class Cycle implements Serializable{
    
    @Id
    @GeneratedValue
    private int idcycle;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    
    
    public Cycle(){}
    
    public Cycle(int idcycle, Date startDate, Date endDate) {
        this.idcycle = idcycle;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    

    public int getIdcycle() {
        return idcycle;
    }

    public void setIdcycle(int idcycle) {
        this.idcycle = idcycle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
