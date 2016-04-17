package gr.athtech.mis.service;

import gr.athtech.mis.model.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import gr.athtech.mis.repository.UserRepository;
import java.util.List;

@Service("userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserRepository repo;

    public List<User> findAll() {
        List<User> users = repo.findAll();

        logger.info("---------Users", users);
        return users;
    }
    
    /*
    public User findById() {

        User user = userRepository.findOne(Long.MIN_VALUE);

        return user;
    }*/
}
