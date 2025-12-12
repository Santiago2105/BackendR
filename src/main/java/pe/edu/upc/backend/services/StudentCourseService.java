package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.StudentCourseDTO;
import pe.edu.upc.backend.dtos.StudentDTO;
import pe.edu.upc.backend.entities.StudentCourse;

import java.util.List;

public interface StudentCourseService {

    public StudentCourseDTO add(StudentCourseDTO studentCourseDTO);
    public List<StudentCourse> listAll();

    public List<StudentCourseDTO> listByStudentId(Long id);

    public void delete(Long id);

    public StudentCourse findById(Long id);

}
