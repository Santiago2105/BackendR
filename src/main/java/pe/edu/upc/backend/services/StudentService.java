package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.StudentDTO;
import pe.edu.upc.backend.entities.Student;

import java.util.List;

public interface StudentService {

    public StudentDTO add(StudentDTO studentDTO);
    public List<StudentDTO> listAll();
    public Student findById(Long id);
    public StudentDTO findByIdDTO(Long id);


}
