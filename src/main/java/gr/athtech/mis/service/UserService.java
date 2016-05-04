package gr.athtech.mis.service;

import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.SecurityUser;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.RoleRepository;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import gr.athtech.mis.repository.UserRepository;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("userService")
public class UserService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserRepository repo;
    @Resource
    RoleRepository roleRepo;

    public List<User> findAll() {
        List<User> users = repo.findAll();

        logger.info("---------Users", users);
        return users;
    }

    public User findById(Long id) {

        User user = repo.findOne(id);

        return user;
    }

    /**
     * Get users based on a certain role
     *
     * @param role
     * @return
     */
    public List<User> findByRole(Role role) {
        List<User> users = repo.findByRoles(role);

        return users;
    }

    /**
     * Return the users who have "medical visitor" as a role
     *
     * @return
     */
    public List<User> getMedicalVisitors() {

        Role medicalVisitor = roleRepo.findByName("Medical Visitor");
        List<User> medicalVisitors = repo.findByRoles(medicalVisitor);

        return medicalVisitors;
    }

    /**
     * Save a new user instance
     *
     * @param user
     * @return
     */
    public User save(User user) {
        user = repo.save(user);
        return user;
    }

    /**
     * Update the user instance
     *
     * @param user
     * @return
     */
    public User update(User user) {
        user = repo.save(user);
        return user;
    }

    /**
     * Delete a user based on Id
     *
     * @param id
     */
    public void delete(Long id) {
        repo.delete(id);
    }

    /**
     * For spring security
     *
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        return new SecurityUser(user);
    }

}
