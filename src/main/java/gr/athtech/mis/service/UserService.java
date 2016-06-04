package gr.athtech.mis.service;

import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;

    /**
     * Get the count of scheduled visits per geolocation area, for a certain
     * medical visitor
     *
     * @param medicalVisitorId
     * @return
     */
    public Map<String, Integer> byGeolocationArea(Long medicalVisitorId) {

        String geoLocationName = "";
        Map<String, Integer> byGeolocationArea = new HashMap<>();

        User medicalVisitor = userRepository.findOne(medicalVisitorId);

        for (ScheduledVisit scheduledVisit : medicalVisitor.getScheduledVisits()) {
            
            geoLocationName = scheduledVisit.getDoctor().getGeolocationArea().getName();
            
            if (byGeolocationArea.containsKey(geoLocationName)) {
                
                if("Paid".equals(scheduledVisit.getStatus())){
                    
                }
                byGeolocationArea.put(geoLocationName, byGeolocationArea.get(geoLocationName) + 1);
            } else {
                byGeolocationArea.put(geoLocationName, 1);
            }
        }

        return byGeolocationArea;
    }

}
