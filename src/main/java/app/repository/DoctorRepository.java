package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import app.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("select d from Doctor d where d.lastname like ?1")
    List<Doctor> findDoctorsByLastnameContaining(String s);
}
