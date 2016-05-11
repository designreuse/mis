/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.Cycle;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmone
 */
@Service("cycleRepository")
public class CycleRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(Cycle.class);
    
    @Resource
    ICycleRepository repo;
    
    public List<Cycle> findAll(){
        List<Cycle> cycles = repo.findAll();
        logger.info("---------CYCLES", cycles);
        return cycles;
    }
    
    public Cycle findOne(Long id){
        Cycle cycle = repo.findOne(id);
        return cycle;
    }
    public Cycle save(Cycle cycle){
        cycle = repo.save(cycle);
        return cycle;
        
    }
    
}
