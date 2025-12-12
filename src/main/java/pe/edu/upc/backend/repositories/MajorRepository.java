package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Major;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByFaculty_Id(Long facultyId);

}
