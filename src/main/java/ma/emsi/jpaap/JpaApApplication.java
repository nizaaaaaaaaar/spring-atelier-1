package ma.emsi.jpaap;

import ma.emsi.jpaap.entities.Patient;
import ma.emsi.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {

    @Autowired // Dependency Injection
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(
                new Patient(null,"Nassim",new Date(),false,56));
        patientRepository.save(
                new Patient(null,"Aicha",new Date(),false,56));


        /*
        Gets all Patients

        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });

         */

        // Get patient with paginaton limit how many patients we return

        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,2));
        System.out.println("Total Pages :"+patients.getTotalPages());
        System.out.println("Total Elements :"+patients.getTotalElements());
        System.out.println("Current Page :"+patients.getNumber());
        List<Patient> content = patients.getContent();
        // Get Sick Patients
        List<Patient> malade = patientRepository.findByMalade(false);
        malade.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });


        System.out.println("************************");

        // Look for a patient
        Patient patient = patientRepository.findById(1L).orElse(null);

        // If patients is not null get infos
        if(patient != null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }

        // Update Score
        patient.setScore(150);
        // save data
        patientRepository.save(patient);
        // Delete Patient by Id
        patientRepository.deleteById(1L);



    }
}
