package facade;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.logging.Logger;

@Component
public class CronJob {

    static final Logger LOGGER =
            Logger.getLogger(CronJob.class.getName());

    //Check all patient for prescriptions that will expire soon.
    @Scheduled(initialDelay = 2000, fixedDelay = 60000)
    public void DoThings() {
        PatientFacade fc = new PatientFacade();
        fc.checkAllPrescription();
        LOGGER.info("Patient with soon expired prescriptions are notified at: " +  LocalTime.now());
    }


    }



