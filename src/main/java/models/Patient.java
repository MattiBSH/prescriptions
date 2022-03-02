package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    private Journal journal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="patient", fetch = FetchType.EAGER )
    private List<Prescription>prescription;

    public Patient() {

    }

    public Patient(Person person, Doctor doctor) {
        this.id = id;
        this.person = person;
        this.doctor = doctor;
        this.prescription = new ArrayList<>();
    }
    public Patient(Person person,Journal journal) {
        this.id = id;
        this.person = person;
        this.prescription = new ArrayList<>();
    }
    public List<Prescription> getPrescription() {
        return prescription;
    }

    public void addPrescription(Prescription p){
        prescription.add(p);
    }
    public void removePrescription(Prescription p){
        prescription.remove(p);
    }
    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Journal getJournal() {
        journal.setJournalAccessedLast(LocalDateTime.now());
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @Override
    public String toString() {
        return "";
    }
}
