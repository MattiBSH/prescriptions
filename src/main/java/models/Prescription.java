package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Prescription")
public class Prescription {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    private Doctor doctor;


    @Column(name = "prescribed")
    private LocalDateTime datePrescribed;

    @Column(name = "expired")
    private LocalDateTime expired;

    @ManyToOne
    private Patient patient;

    @Column(name = "pharmacyAddress")
    private String pharmacyAddress;

    @Column(name= "medicin")
    private String medicin;

    public String getMedicin() {
        return medicin;
    }

    public void setMedicin(String medicin) {
        this.medicin = medicin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prescription() {

    }
    public Prescription(Doctor doctor, LocalDateTime datePrescribed, LocalDateTime expired, String pharmacyAddress,Patient p) {
        this.doctor = doctor;
        this.datePrescribed = datePrescribed;
        this.expired = expired;
        this.pharmacyAddress=pharmacyAddress;
        this.patient=p;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDateTime datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Prescriptions{" +
                "doctor=" + doctor +
                ", datePrescribed=" + datePrescribed +
                ", expired=" + expired +
                '}';
    }
}
