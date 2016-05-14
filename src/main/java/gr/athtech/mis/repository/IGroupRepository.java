package gr.athtech.mis.repository;

import gr.athtech.mis.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iGroupRepository")
public interface IGroupRepository extends JpaRepository<Group, Long>{
    
}
