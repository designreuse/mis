package gr.athtech.mis.repository;

import gr.athtech.mis.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("doctorRepository")
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    
    @Query("SELECT d FROM Doctor d "
            + "WHERE NOT EXISTS "
            + "(SELECT s FROM ScheduledVisit s WHERE d = s)")
    public List<Doctor> getAvailableDoctors();
    
}
