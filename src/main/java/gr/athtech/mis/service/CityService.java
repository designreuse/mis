package gr.athtech.mis.service;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.CityRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("cityService")
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Resource
    CityRepository repo;

    public List<City> findAll() {
        List<City> cities = repo.findAll();

        return cities;
    }
}
