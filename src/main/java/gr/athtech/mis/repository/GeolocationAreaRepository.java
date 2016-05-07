package gr.athtech.mis.repository;

import gr.athtech.mis.model.GeolocationArea;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("geolocationAreaRepository")
public class GeolocationAreaRepository {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationAreaRepository.class);

    @Resource
    IGeolocationAreaRepository repo;

    public List<GeolocationArea> findAll() {
        List<GeolocationArea> geolocationAreas = repo.findAll();

        return geolocationAreas;
    }

    public GeolocationArea findOne(Long id) {
        GeolocationArea geolocationArea = repo.findOne(id);

        return geolocationArea;
    }

    /**
     * Fetch a geolocationArea by its id, and also load its cities and
     * institutions
     *
     * @param id
     * @return
     */
    public GeolocationArea findOneWithCitiesWithInstitutions(Long id) {
        GeolocationArea geolocationArea = repo.findOne(id);

        if (geolocationArea != null) {
            geolocationArea.getCities();
            geolocationArea.getInstitutions();
        }
        
        return geolocationArea;
    }
}
