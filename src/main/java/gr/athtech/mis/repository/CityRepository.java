package gr.athtech.mis.repository;

import gr.athtech.mis.model.City;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Uses the methods of ICityRepository interface
 * 
 * @author xrist
 */
@Service("cityRepository")
public class CityRepository {

    private static final Logger logger = LoggerFactory.getLogger(CityRepository.class);

    @Resource
    ICityRepository repo;

    /**
     * Find all cities
     * 
     * @return List<City> a list of cities
     */
    public List<City> findAll() {
        List<City> cities = repo.findAll();

        return cities;
    }
    
    /**
     * Find a city based on a given id
     * 
     * @param id
     * @return City 
     */
    public City findOne(Long id) {
        City city = repo.findOne(id);

        return city;
    }
}
