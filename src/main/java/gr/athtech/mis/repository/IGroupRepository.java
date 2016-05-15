package gr.athtech.mis.repository;

import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("iGroupRepository")
public interface IGroupRepository extends JpaRepository<Group, Long>{
    
    List<Group> findByLeader(User leader);
    
    @Query("SELECT g FROM Group g "
            + "JOIN g.members m "
            + "WHERE g.leader.id = ?1 OR m.id = ?1")
    List<Group> findByLeaderOrMember(Long id);
}
