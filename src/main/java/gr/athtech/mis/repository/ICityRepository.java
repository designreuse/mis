package gr.athtech.mis.repository;

import gr.athtech.mis.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iCityRepository")
public interface ICityRepository extends JpaRepository<City, Long>{
    
}
