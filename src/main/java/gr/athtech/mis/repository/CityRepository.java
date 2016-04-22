package gr.athtech.mis.repository;

import gr.athtech.mis.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Long>{
    
}
