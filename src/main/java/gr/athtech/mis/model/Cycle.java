/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.model;

import java.sql.Date;
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
@Table(name = "city")
public class Cycle {
    
    @Id
    @GeneratedValue
    private Long idcycle;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    
    public Cycle(Long idcycle, Date startDate, Date endDate) {
        this.idcycle = idcycle;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    

    public Long getIdcycle() {
        return idcycle;
    }

    public void setIdcycle(Long idcycle) {
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
