package gr.athtech.mis.service;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.repository.GeolocationAreaRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("geolocationAreaService")
public class GeolocationAreaService {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationAreaService.class);

    @Resource
    GeolocationAreaRepository repo;

    public List<GeolocationArea> findAll() {
        List<GeolocationArea> geolocationAreas = repo.findAll();

        return geolocationAreas;
    }

    public GeolocationArea findOne(Long id) {
        GeolocationArea geolocationArea = repo.findOne(id);

        return geolocationArea;
    }
}
