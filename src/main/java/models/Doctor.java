package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="doctor")
    private List<Patient>patients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="doctor")
    private List<Prescription>prescriptions;

    public Doctor() {

    }

    public Doctor(Person person) {
        this.person = person;
        this.patients = new ArrayList<>();
        this.prescriptions=new ArrayList<>();
    }

    public void addPatient(Patient p){
        patients.add(p);
    }

    public void addPrescription(Prescription p){
        prescriptions.add(p);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                '}';
    }
}
