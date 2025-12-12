package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
