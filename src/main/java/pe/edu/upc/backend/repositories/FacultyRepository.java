package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Faculty;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByName(String name);
    List<Faculty> findByDirector(String director);

}
