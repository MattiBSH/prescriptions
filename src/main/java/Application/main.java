package Application;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import models.Doctor;
import models.Patient;
import models.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class main {
    public static void main(String[] args) {
        main.populateDatabase();

        /*
        Doctor d =  new Doctor(p);
        Patient patient = new Patient(p,d);*/
        //pf.createDoctor(d);
        //pf.createPatient(p);
        //pf.findRandomDoctor();
       //pf.createPatient(p,d);
        //pf.addPrescriptionToUser(1,new Prescription(d,LocalDateTime.now(),LocalDateTime.now(),"Ellegårdsvej 21",patient));

        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");





        Prescription prescription = new Prescription(d, LocalDateTime.now(),LocalDateTime.now(),"Ellegårdsvej 100",patient);

        patient.setPerson(p);
        patient.setDoctor(d);
        p.setPatient(patient);
        d.addPatient(patient);
        d.addPrescription(prescription);
        patient.addPrescription(prescription);*/


        /*entityManager.persist(prescription);
        entityManager.persist(d);
        entityManager.persist(patient);*/
        //entityManager.persist(p);


//        patient.addPrescription(prescription);
//        //1mil 12 26 - 12 51
//        for (int i = 0; i < 1000000; i++) {
//            Person p = Application.generateUser.createPerson();
//            entityManager.persist(p);
//        }
        //*entityManager.persist(prescription);
       /* entityManager.persist(d);
        entityManager.persist(patient);
        entityManager.persist(p);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
*/

    }
    public static void populateDatabase(){
        //This code should create 100 doctors and 1000 people/patients. No prescriptions.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Doctor doc = null;
            for (int j = 0; j < 10; j++) {
                Name name = faker.name();
                int age = faker.number().numberBetween(1,100);
                String gender = "";
                if(faker.bool().bool()){
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                Person p = new Person(name.firstName(), name.lastName(), age, gender);
                p.setCpr(generateUser.socialSecurity(age, gender));
                Patient pt = null;
                if(j<1){
                    doc = new Doctor(p);
                } else{
                    pt = new Patient(p, doc);
                    doc.addPatient(pt);
                    p.setPatient(pt);
                    entityManager.persist(pt);
                }
                entityManager.persist(p);
            }
            entityManager.persist(doc);
        }


        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
