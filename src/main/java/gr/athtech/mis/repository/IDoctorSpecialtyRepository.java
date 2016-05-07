package gr.athtech.mis.repository;

import gr.athtech.mis.model.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("iDoctorSpecialtyRepository")
public interface IDoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, Long>{
    
}
