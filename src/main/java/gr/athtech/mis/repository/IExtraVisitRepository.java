package gr.athtech.mis.repository;

import gr.athtech.mis.model.ExtraVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iExtraVisitRepository")
public interface IExtraVisitRepository extends JpaRepository<ExtraVisit, Long>{
    
}
