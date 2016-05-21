package gr.athtech.mis.service;

import gr.athtech.mis.model.Role;
import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.SecurityUser;
import gr.athtech.mis.model.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import gr.athtech.mis.repository.IUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("authService")
public class AuthService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Resource
    IUserRepository repo;

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

    /**
     * Check if a logged in user is admin
     *
     * @param username
     * @return
     */
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = new SecurityUser(repo.findByUsername(auth.getName()));

        boolean flag = false;

        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * Check if the logged in user is a medical visitor
     *
     * @return
     */
    public boolean isMedicalVisitor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = new SecurityUser(repo.findByUsername(auth.getName()));

        boolean flag = false;

        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_MEDICAL_VISITOR")) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * Return the id of the logged in user
     * 
     * @return 
     */
    public Long getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = new SecurityUser(repo.findByUsername(auth.getName()));

        return user.getId();
    }

    /**
     * Check if the logged in user may edit a given doctor
     *
     * @param id
     * @return
     */
    public boolean canEditDoctor(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByUsername(auth.getName());
        boolean flag = false;

        for (ScheduledVisit scheduledVisit : user.getScheduledVisits()) {
            if (id == scheduledVisit.getDoctor().getId()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
