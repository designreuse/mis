/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.service;

import gr.athtech.mis.model.Cycle;
import gr.athtech.mis.repository.CycleRepository;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmone
 */
@Service("cycleService")
public class CycleService {
    
    private static final Logger logger = LoggerFactory.getLogger(Cycle.class);
    
    @Resource
    CycleRepository repo;
    
    public List<Cycle> findAll(){
        List<Cycle> cycles = repo.findAll();
        logger.info("---------CYCLES", cycles);
        return cycles;
    }
    
    public Cycle findOne(Long id){
        Cycle cycle = repo.findOne(id);
        return cycle;
    }
    
}
