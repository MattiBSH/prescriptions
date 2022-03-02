package Application;

import DTOs.JournalDto;
import facade.PatientFacade;
import models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
@EnableAsync
@EnableScheduling
@SpringBootApplication
@RestController
@Configuration
@ComponentScan(basePackages="facade")
public class MyApplication {
    Log log = new Log("log");

    public MyApplication() throws IOException {
    }


    @RequestMapping("/randomDoctor")
    String random() {
        PatientFacade patientFacade = new PatientFacade();
        Doctor doctor =patientFacade.findRandomDoctor();
        return "Hello "+doctor.getPerson().getFirstname()+ " "+ doctor.getPerson().getLastname();
    }

    @RequestMapping("/patient/{id}")
    @ResponseBody
    String patientByID(@PathVariable long id) {
        PatientFacade patientFacade = new PatientFacade();
        Patient patient =patientFacade.findPatientById(id);
        if(patient!=null){
            log.logger.info("Patient with id: "+id+" was accessed");
            log.logger.setLevel(Level.INFO);
            return "Hello "+patient.getPerson().getFirstname()+ " "+ patient.getPerson().getLastname();
        }else {
            return "<h1 align='center'>No user with that id</h1>";
        }
    }

    @PostMapping("/makePrescription/{id}")
    String prescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        PatientFacade patientFacade = new PatientFacade();
        patientFacade.addPrescriptionToUser(id, prescription);
        log.logger.info("Prescription was made for user with id: "+id+" By doctor with id: "+prescription.getDoctor().getId());
        log.logger.setLevel(Level.INFO);
        return "Success";
    }
    @GetMapping("/patientJournal/{id}")
    JournalDto readJournal(@PathVariable Long id, @RequestBody Doctor doctorObject) {
        PatientFacade patientFacade = new PatientFacade();
        Journal patientJournal=patientFacade.findJournalbyPatientId(id,doctorObject.getId());
        log.logger.info("Journal for user: "+id+" was accessed by Doctor with id: "+doctorObject.getId());
        log.logger.setLevel(Level.INFO);
        JournalDto journalDto= new JournalDto(patientJournal.getId(),patientJournal.getJournalAccessedLast(),patientJournal.getPastAilmentsDescribed());

        return journalDto;
    }
    @PostMapping("/sendEmailToPatient/{id}")
    String SendEmail(@PathVariable Long id, @RequestBody Email email) {
        PatientFacade patientFacade = new PatientFacade();
        Patient patient = patientFacade.findPatientById(id);
        patientFacade.SendEmailToPatient(email, patient);
        log.logger.info("An email was sent to" + patient.getPerson().getFirstname() + " " + patient.getPerson().getLastname());
        log.logger.setLevel(Level.INFO);
        return "An email was sent to" + patient.getPerson().getFirstname() + " " + patient.getPerson().getLastname();


    }


    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);

    }

}