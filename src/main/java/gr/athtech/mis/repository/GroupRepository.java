package gr.athtech.mis.repository;

import gr.athtech.mis.model.Group;
import gr.athtech.mis.model.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Uses the methods of the IGroupRepository interface
 * 
 * @author xrist
 */
@Service("groupRepository")
public class GroupRepository {

    private static final Logger logger = LoggerFactory.getLogger(GroupRepository.class);

    @Resource
    IGroupRepository repo;

    /**
     * Find all groups
     * 
     * @return List<Group> 
     */
    public List<Group> findAll() {
        List<Group> groups = repo.findAll();

        return groups;
    }

    /**
     * Find a group based on a given id
     * 
     * @param id
     * @return Group
     */
    public Group findOne(Long id) {
        Group group = repo.findOne(id);
        
        if(group!=null){
            group.getLeader();
            group.getMembers();
        }
        
        return group;
    }
    
    /**
     * Find groups by their leader
     * 
     * @param leader
     * @return List<Group>
     */
    public List<Group> findByLeader(User leader){
        List<Group> userGroups = repo.findByLeader(leader);
        
        return userGroups;
    }
    
    /**
     * Find groups by their members
     * 
     * @param id
     * @return List<Group>
     */
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
    
    /**
     * Save a group
     * 
     * @param group
     * @return Group
     */
    public Group save(Group group){
        return repo.save(group);
    }
    
    /**
     * Update a group
     * 
     * @param group
     * @return Group
     */
    public Group update(Group group){
        return repo.save(group);
    }
    
    /**
     * Delete a group
     * 
     * @param id 
     */
    public void delete(Long id){
        repo.delete(id);
    }
    
    public Long findByUserIdUnique(Long id){
        Long userGroups = repo.findByMemberUnique(id);
        
        return userGroups;
    }
}
