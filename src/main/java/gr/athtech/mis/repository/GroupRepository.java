package gr.athtech.mis.repository;

import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("groupRepository")
public class GroupRepository {

    private static final Logger logger = LoggerFactory.getLogger(GroupRepository.class);

    @Resource
    IGroupRepository repo;

    public List<Group> findAll() {
        List<Group> groups = repo.findAll();

        return groups;
    }

    public Group findOne(Long id) {
        Group group = repo.findOne(id);
        
        if(group!=null){
            group.getLeader();
            group.getMembers();
        }
        
        return group;
    }
    
    public List<Group> findByLeader(User leader){
        List<Group> userGroups = repo.findByLeader(leader);
        
        return userGroups;
    }
    
    public List<Group> findByUserId(Long id){
        List<Group> userGroups = repo.findByMember(id);
        
        return userGroups;
    }
    
    public List<Group> findByLeaderId(Long id){
        List<Group> userGroups = repo.findByLeaderSQL(id);
        
        return userGroups;
    }
    
    public List<Group> findAllById(Long id){
        List<Group> userGroups = repo.findAllGroups(id);
        
        return userGroups;
    }
    
    public Group save(Group group){
        return repo.save(group);
    }
    
    public Group update(Group group){
        return repo.save(group);
    }
    
    public void delete(Long id){
        repo.delete(id);
    }
    
    public Long findByUserIdUnique(Long id){
        Long userGroups = repo.findByMemberUnique(id);
        
        return userGroups;
    }
}
