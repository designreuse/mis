package gr.athtech.mis.repository;

import gr.athtech.mis.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("iDoctorRepository")
public interface IDoctorRepository extends JpaRepository<Doctor, Long>{
        
    @Query("SELECT d FROM Doctor d "
            + "WHERE NOT EXISTS "
            + "(SELECT s FROM d.scheduledVisits s)")
    public List<Doctor> getAvailableDoctors();
    
    @Query("SELECT d FROM Doctor d "
            + "WHERE NOT EXISTS "
            + "(SELECT s FROM d.scheduledVisits s WHERE s.cycle.id = ?1)")
    public List<Doctor> getAvailableDoctorsList(Long id);
    
}
