package Application;

import facade.PatientFacade;
import models.Doctor;
import models.Patient;
import models.Person;
import models.Prescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class generateUser {
    static String[] firstNames =
            {
            "Aare","Aarika","Abagael","Abagail","Abbe", "Abbey","Abbi","Abbie","Abby","Abbye", "Abigael", "Abigail","matti","Ben","Bob"
    };

    static String[] lastNames={"Smith","Johnson","Williams","Brown","Jones","Miller","Davis","Garcia","Rodriguez","Wilson","Martinez","Anderson","Taylor","Thomas","Hernandez","Moore","Martin","Jackson","Thompson","White","Lopez","Lee","Gonzalez","Harris","Clark","Lewis","Robinson","Walker","Perez","Hall","Young","Allen","Sanchez","Wright","King","Scott","Green","Baker","Adams","Nelson","Hill","Ramirez","Campbell","Mitchell","Roberts","Carter","Phillips","Evans","Turner","Torres","Parker","Collins","Edwards","Stewart","Flores","Morris","Nguyen","Murphy","Rivera","Cook","Rogers","Morgan","Peterson","Cooper","Reed","Bailey","Bell","Gomez","Kelly","Howard","Ward","Cox","Diaz","Richardson","Wood","Watson","Brooks","Bennett","Gray","James","Reyes","Cruz","Hughes","Price","Myers","Long","Foster","Sanders","Ross","Morales","Powell","Sullivan","Russell","Ortiz","Jenkins","Gutierrez","Perry","Butler","Barnes","Fisher","Henderson","Coleman","Simmons","Patterson","Jordan","Reynolds","Hamilton","Graham","Kim","Gonzales","Alexander","Ramos","Wallace","Griffin","West","Cole","Hayes","Chavez","Gibson","Bryant","Ellis","Stevens","Murray","Ford","Marshall","Owens","Mcdonald","Harrison","Ruiz","Kennedy","Wells","Alvarez","Woods","Mendoza","Castillo","Olson","Webb","Washington","Tucker","Freeman","Burns","Henry"};

    public static void main(String[] args) {
       // Person p = createPerson();

        PatientFacade fc = new PatientFacade();
        //Patient p = fc.findPatientById(11);
        fc.checkAllPrescription();


        //Doctor d = fc.findRandomDoctor();
        //System.out.println(fc.checkPrescriptionExpiration(p));
        //fc.getAllPatients();
        //Prescription pre = new Prescription(p.getDoctor(),LocalDateTime.now(), LocalDateTime.now(), "Ellegårdsvej", p);
        //fc.addPrescriptionToUser(11,pre);



        //fc.createPatient(p);


       // System.out.println(p.toString());


    }

    public static String makeFirstName() {
    Random rand= new Random();
    int random = rand.nextInt(firstNames.length);
    return firstNames[random];
    }

    public static String makeLastName() {
        Random rand= new Random();
        int random = rand.nextInt(lastNames.length);
        return lastNames[random];
    }

    public static int makeAge() {
        Random rand= new Random();
        int random = rand.nextInt(99);
        return random;
    }


    public static String socialSecurity(int nr, String gender) {
        Random rand= new Random();
        String last3Digits= String.valueOf(rand.nextInt(100,999));
        int lastDigit = rand.nextInt(0,9);
        if(lastDigit % 2 == 0 && gender.equals("Male")){
                lastDigit++;
        } else if(lastDigit % 2 != 0 && gender.equals("Female")){
            lastDigit--;
        }
        LocalDateTime dt = LocalDateTime.now().minusYears(nr);
        LocalDate startYear = LocalDate.of(dt.getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(dt.getYear(), 12, 31);
        LocalDate randomBirthDate = between(startYear, endYear);
        String year = String.valueOf(randomBirthDate.getYear()).substring(2);
        String month = String.valueOf(randomBirthDate.getMonthValue());
        String day = String.valueOf(randomBirthDate.getDayOfMonth());
        if(month.length()<2){
            month="0"+month;
        }
        if(day.length()<2){
            day="0"+day;
        }
        return day + month + year + "-" + last3Digits + lastDigit;
    }
    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static Person createPerson(){
        Person person= new Person(makeFirstName(),makeLastName(),makeAge(), "");
        person.setCpr( socialSecurity(person.getAge(),"")); //Revisit hvis denne metode skal bruges
        person.setEmail(person.getFirstname() + "@mail.dk");
        return person;
    }



}
