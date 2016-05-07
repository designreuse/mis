package gr.athtech.mis.repository;

import gr.athtech.mis.model.GeolocationArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iGeolocationAreaRepository")
public interface IGeolocationAreaRepository extends JpaRepository<GeolocationArea, Long>{
    
}
