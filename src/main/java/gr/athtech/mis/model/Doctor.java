package gr.athtech.mis.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jmone
 */
@Entity
public class Doctor implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String position;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="city_id", nullable = false)
    private City doc_city;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="specialty_id", nullable = false)
    private DoctorSpecialty doc_specialty;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="area_id", nullable = false)
    private GeolocationArea doc_area;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="institucion_id", nullable = false)
    private Institucion doc_institucion;
    @OneToMany(mappedBy="visitingDoctor")
    private List<ScheduledVisit> schVisits;


    public Doctor(){}


    public Doctor(int id, String name, String address, String phone, String position, City doc_city, DoctorSpecialty doc_specialty, GeolocationArea doc_area, Institucion doc_institucion, List<ScheduledVisit> schVisits) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.position = position;
        this.doc_city = doc_city;
        this.doc_specialty = doc_specialty;
        this.doc_area = doc_area;
        this.doc_institucion = doc_institucion;
        this.schVisits = schVisits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public City getDoc_city() {
        return doc_city;
    }

    public void setDoc_city(City doc_city) {
        this.doc_city = doc_city;
    }

    public DoctorSpecialty getDoc_specialty() {
        return doc_specialty;
    }

    public void setDoc_specialty(DoctorSpecialty doc_specialty) {
        this.doc_specialty = doc_specialty;
    }

    public GeolocationArea getDoc_area() {
        return doc_area;
    }

    public void setDoc_area(GeolocationArea doc_area) {
        this.doc_area = doc_area;
    }

    public Institucion getDoc_institucion() {
        return doc_institucion;
    }

    public void setDoc_institucion(Institucion doc_institucion) {
        this.doc_institucion = doc_institucion;
    }

    public List<ScheduledVisit> getSchVisits() {
        return schVisits;
    }

    public void setSchVisits(List<ScheduledVisit> schVisits) {
        this.schVisits = schVisits;
    }
    
}
