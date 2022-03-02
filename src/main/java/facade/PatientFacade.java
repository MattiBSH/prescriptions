package facade;
import models.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatientFacade {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    EntityManager entityManager= entityManagerFactory.createEntityManager();

    //creates doctor
    public void createDoctor(Doctor doctor){
        entityManager.getTransaction().begin();
        entityManager.persist(doctor);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

    }

    public void createPatient(Person person){
        entityManager.getTransaction().begin();
        Journal journal= new Journal();
        Patient patient = new Patient(person,journal);
        patient.setPerson(person);
        person.setPatient(patient);
        patient.setDoctor(findRandomDoctor());
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

    }

    public Doctor findRandomDoctor(){
        Random rand = new Random();
        List<Doctor> d = entityManager.createQuery("from Doctor d", Doctor.class).getResultList();
        int randomInt= rand.nextInt(0,d.size());
        return d.get(randomInt);
    }

    public Patient findPatientById(long id){
        Patient p = entityManager.find(Patient.class,id);
        System.out.println(p);
        return p;
    }
    public Journal findJournalbyPatientId(long id,long doctorId){
        entityManager.getTransaction().begin();
        Patient p = entityManager.find(Patient.class,id);
        System.out.println(p);
        Doctor d = entityManager.find(Doctor.class,doctorId);
        System.out.println(d);
        p.getJournal().setLastAccessedBy(d);
        entityManager.getTransaction().commit();
        return p.getJournal();
    }

    public void addPrescriptionToUser(long patientID,Prescription prescription){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Patient p = em.find(Patient.class, patientID);
        prescription.setDoctor(p.getDoctor());
        prescription.setPatient(p);
        prescription.setDatePrescribed(LocalDateTime.now());
        prescription.setExpired(LocalDateTime.now().plusWeeks(2));
        p.addPrescription(prescription);
        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();
    }

    public void removePrescriptionToUser(long patientID,Prescription prescription){
        entityManager.getTransaction().begin();
        Patient p= entityManager.find(Patient.class,patientID);
        p.removePrescription(prescription);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    public void SendEmailToPatient(Email email, Patient patient) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.freesmtpservers.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.transport.protocol", "smtp");
        String patientEmail = patient.getPerson().getEmail();
        Session session = Session.getDefaultInstance(properties);
        String subject = email.getSubject();
        String content = email.getContent();
        try {
            if(subject == "" || content == "") {
                Logger.getLogger("both subject and content need to be filled");
            }
            MimeMessage mime = new MimeMessage(session);
            mime.setRecipient(Message.RecipientType.TO, new InternetAddress(patientEmail));
            mime.setSubject(subject);
            mime.setText(content);
            Transport transport = session.getTransport("smtp");
            transport.send(mime);
            transport.close();
            Logger.getLogger(Level.INFO.getName());
        } catch (MessagingException e) {
            Logger.getLogger("NoSuchProviderException " + e.getMessage());
            Logger.getLogger("NoSuchProviderException " + e.getStackTrace());


        }
    }

    public void checkPrescriptionExpiration(Patient patient) {
        for (int i = 0; i < patient.getPrescription().size(); i++) {
            if(patient.getPrescription().get(i).getExpired().minusDays(10).isBefore(LocalDateTime.now()) && patient.getPerson().getEmail() != null ) {
                Email e = new Email("Prescripton will soon expire", "You prescription: "
                        + patient.getPrescription().get(i).toString() + "will soon expire. Pick it up or get it extended.");
                System.out.println(patient.getId());
                SendEmailToPatient(e, patient);

            }
        }

    }

    private List<Patient> getAllPatients() {
        List<Patient> allPatients = entityManager.createQuery("from Patient p", Patient.class).getResultList();
        entityManager.close();
        return allPatients;

    }

    public void checkAllPrescription() {
        List<Patient> patients = getAllPatients();
        PatientFacade fc = new PatientFacade();
        for (Patient p: patients
             ) {
            fc.checkPrescriptionExpiration(p);


        }
    }
}
