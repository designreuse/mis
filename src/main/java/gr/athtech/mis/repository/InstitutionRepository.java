package gr.athtech.mis.repository;

import gr.athtech.mis.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("institutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution, Long>{
    
}
