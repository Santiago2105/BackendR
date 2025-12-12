package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.StudentCourse;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    public List<StudentCourse> findByStudentId(Long id);

}
