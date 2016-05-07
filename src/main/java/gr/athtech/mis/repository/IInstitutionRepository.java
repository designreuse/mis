package gr.athtech.mis.repository;

import gr.athtech.mis.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iInstitutionRepository")
public interface IInstitutionRepository extends JpaRepository<Institution, Long>{
    
}
