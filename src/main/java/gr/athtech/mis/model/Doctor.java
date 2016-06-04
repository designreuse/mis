package gr.athtech.mis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Holds the doctor information
 * 
 * @author jmone
 */
@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String position;

    @Column
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    @JsonIgnore
    private DoctorSpecialty specialty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geolocation_area_id")
    @JsonIgnore
    private GeolocationArea geolocationArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    @JsonIgnore
    private Institution institution;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ScheduledVisit> scheduledVisits;
    

    @Transient
    private boolean editable;
    @Transient
    private boolean deletable;
    @Transient
    private String cityName;
    @Transient
    private String geolocationAreaName;
    @Transient
    private String institutionName;
    @Transient
    private String specialtyName;

    public Doctor() {
    }

    public Doctor(Long id, String firstName, String lastName, String address, String phone, String position, String email, City city, DoctorSpecialty specialty, GeolocationArea geolocationArea, Institution institution, List<ScheduledVisit> scheduledVisits) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.position = position;
        this.email = email;
        this.city = city;
        this.specialty = specialty;
        this.geolocationArea = geolocationArea;
        this.institution = institution;
        this.scheduledVisits = scheduledVisits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public DoctorSpecialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(DoctorSpecialty specialty) {
        this.specialty = specialty;
    }

    public GeolocationArea getGeolocationArea() {
        return geolocationArea;
    }

    public void setGeolocationArea(GeolocationArea geolocationArea) {
        this.geolocationArea = geolocationArea;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<ScheduledVisit> getScheduledVisits() {
        return scheduledVisits;
    }

    public void setScheduledVisits(List<ScheduledVisit> scheduledVisits) {
        this.scheduledVisits = scheduledVisits;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setIsEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setIsDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getGeolocationAreaName() {
        return geolocationAreaName;
    }

    public void setGeolocationAreaName(String geolocationAreaName) {
        this.geolocationAreaName = geolocationAreaName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

}
