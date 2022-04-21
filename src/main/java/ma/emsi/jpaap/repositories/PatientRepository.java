package ma.emsi.jpaap.repositories;

import ma.emsi.jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

// Mapping object Spring Data
public interface PatientRepository extends JpaRepository<Patient, Long /* The type of the Id  */ > {

    // Returns patiens based on malade
    List<Patient> findByMalade(boolean m);
    //Page<Patient> findByMalade(boolean m, Pageable pageable);
    List<Patient> findByMaladeAndScoreLessThan(boolean m , int score);
    List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);
    List<Patient> findByDateNaissanceBetween(Date d1, Date d2);

    // Custom functions
    @Query("select p from Patient p where p.nom = :x")
    List<Patient> chercherPatients(@Param("x") String nom);

}
