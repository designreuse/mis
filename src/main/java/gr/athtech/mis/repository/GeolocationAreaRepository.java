package gr.athtech.mis.repository;

import gr.athtech.mis.model.GeolocationArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("geolocationAreaRepository")
public interface GeolocationAreaRepository extends JpaRepository<GeolocationArea, Long>{
    
}
