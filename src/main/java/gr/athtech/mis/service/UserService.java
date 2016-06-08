package gr.athtech.mis.service;

import gr.athtech.mis.model.ScheduledVisit;
import gr.athtech.mis.model.User;
import gr.athtech.mis.model.dto.VisitsCount;
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
        Map<String, VisitsCount> byGeolocationAreaCount = new HashMap<>();

        User medicalVisitor = userRepository.findOne(medicalVisitorId);

        for (ScheduledVisit scheduledVisit : medicalVisitor.getScheduledVisits()) {

            geoLocationName = scheduledVisit.getDoctor().getGeolocationArea().getName();

            if (byGeolocationAreaCount.containsKey(geoLocationName)) {

                if ("Paid".equals(scheduledVisit.getStatus())) {
                    byGeolocationAreaCount.get(geoLocationName).setPaidVisitsCount(1);
                } else {
                    byGeolocationAreaCount.get(geoLocationName).setScheduledVisitsCount(byGeolocationAreaCount.get(geoLocationName).getScheduledVisitsCount() + 1);
                }
            } else {
                byGeolocationAreaCount.put(geoLocationName, new VisitsCount(1, 1));
            }
        }

        Map<String, Integer> byGeolocationArea = new HashMap<>();

        for (Map.Entry<String, VisitsCount> area : byGeolocationAreaCount.entrySet()) {
            int percent = (area.getValue().getPaidVisitsCount() * 100) / area.getValue().getScheduledVisitsCount();

            byGeolocationArea.put(area.getKey(), percent);
        }

        return byGeolocationArea;
    }

}
