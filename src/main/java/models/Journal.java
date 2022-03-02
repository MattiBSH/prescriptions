package models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Journal")
public class Journal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    private Doctor lastAccessedBy;

    @Column(name = "journalAccessedLast")
    private LocalDateTime journalAccessedLast;

    @Column(name = "pastAilmentsDescribed")
    private String pastAilmentsDescribed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Journal() {
    }

    public Journal(Patient patient, String pastAilmentsDescribed) {
        this.patient = patient;
        this.pastAilmentsDescribed = pastAilmentsDescribed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getLastAccessedBy() {
        return lastAccessedBy;
    }

    public void setLastAccessedBy(Doctor lastAccessedBy) {
        this.lastAccessedBy = lastAccessedBy;
    }

    public LocalDateTime getJournalAccessedLast() {
        return journalAccessedLast;
    }

    public void setJournalAccessedLast(LocalDateTime journalAccessedLast) {
        this.journalAccessedLast = journalAccessedLast;
    }

    public String getPastAilmentsDescribed() {
        return pastAilmentsDescribed;
    }

    public void setPastAilmentsDescribed(String pastAilmentsDescribed) {
        this.pastAilmentsDescribed = pastAilmentsDescribed;
    }
}
