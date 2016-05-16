package gr.athtech.mis.repository;

import gr.athtech.mis.model.City;
import gr.athtech.mis.model.Doctor;
import gr.athtech.mis.model.DoctorSpecialty;
import gr.athtech.mis.model.GeolocationArea;
import gr.athtech.mis.model.Institution;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("iDoctorRepository")
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

    public List<Doctor> findByFirstNameLikeAndLastNameLikeOrAddressLike(String firstName, String lastName, String address);

    public List<Doctor> findByFirstNameLikeOrLastNameLikeOrAddressLikeOrPhoneLikeOrPositionLikeOrEmailLikeOrCityOrGeolocationAreaOrInstitutionOrSpecialty(String firstName, String lastName, String address, String phone, String position, String email, City city, GeolocationArea geolocationArea, Institution institution, DoctorSpecialty specialty);

    @Query("SELECT d FROM Doctor d "
            + "WHERE NOT EXISTS "
            + "(SELECT s FROM d.scheduledVisits s)")
    public List<Doctor> getAvailableDoctors();

    @Query("SELECT d FROM Doctor d "
            + "WHERE NOT EXISTS "
            + "(SELECT s FROM d.scheduledVisits s WHERE s.cycle.id = ?1)")
    public List<Doctor> getAvailableDoctorsList(Long id);

}
