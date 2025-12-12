package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.backend.entities.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findByCreditsGreaterThan(Integer credits);

    List<Student> findByCreditsBetween(Integer creditsMin, Integer creditsMax);

    List<Student> findByNameContaining(String name);

    List<Student> findByCreditsBetweenOrderByNameAsc(Integer creditsMin, Integer creditsMax);

    //Query Method
    List<Student> findByCity(String city);

    //SQL Nativo
    @Query(value = "SELECT * FROM students WHERE city=?1", nativeQuery = true)
    List<Student> findByCitySQL(String city);

    //JPQL Nativo
    @Query(value ="SELECT s FROM Student s WHERE s.city=?1", nativeQuery = false)
    List<Student> findByCityJPQL(String city);




}
