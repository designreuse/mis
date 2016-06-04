/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.athtech.mis.repository;

import gr.athtech.mis.model.Cycle;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Used the methods of the ICycleRepository interface
 * 
 * @author jmone
 */
@Service("cycleRepository")
public class CycleRepository {

    private static final Logger logger = LoggerFactory.getLogger(Cycle.class);

    @Resource
    ICycleRepository repo;

    /**
     * Find all cycles
     * 
     * @return List<Cycle>
     */
    public List<Cycle> findAll() {
        List<Cycle> cycles = repo.findAll();
        logger.info("---------CYCLES", cycles);
        return cycles;
    }

    /**
     * Find one Cycle based on a given id
     * 
     * @param id
     * @return Cycle
     */
    public Cycle findOne(Long id) {
        Cycle cycle = repo.findOne(id);
        return cycle;
    }

    /**
     * Save a cycle
     * 
     * @param cycle
     * @return Cycle
     */
    public Cycle save(Cycle cycle) {
        cycle = repo.save(cycle);
        return cycle;

    }

    /**
     * Get the current cycle, that is the one that hasn't ended yet.
     * 
     * @return Cycle
     * @throws ParseException 
     */
    public Cycle getCurrentCycle() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        logger.debug("=====today: {}", dateFormat.parse(dateFormat.format(date)));
        Cycle cycle = repo.getCurrentCycle();

        logger.debug("======= current cycle: {}", cycle.getId());

        return cycle;
    }

    /**
     * Delete a paid visit based on Id
     *
     * @param id
     */
    public void delete(Long id) {
        repo.delete(id);
    }

}
