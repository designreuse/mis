package gr.athtech.mis.repository;

import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByUsername(String username);
    
    List<User> findByRoles(Role role);
}
