package gr.athtech.mis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jmone
 */
@Entity
@Table(name = "doctor")
public class Doctor implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String position;
    @Column
    private City city;
    @Column
    private DoctorSpecialty specialty;
    @Column
    private GeolocationArea area;
    @Column
    private Institucion institucion;
    
    public Doctor(){}

    public Doctor(Long id, String name, String address, String phone, String position, City city, DoctorSpecialty specialty, GeolocationArea area, Institucion institucion) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.position = position;
        this.city = city;
        this.specialty = specialty;
        this.area = area;
        this.institucion = institucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public GeolocationArea getArea() {
        return area;
    }

    public void setArea(GeolocationArea area) {
        this.area = area;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    } 
}
