package pe.edu.upc.backend.services;

import pe.edu.upc.backend.entities.Course;
import pe.edu.upc.backend.entities.Faculty;

import java.util.List;

public interface CourseService {

    public List<Course> listAll();
    public Course findById(Long id);
}
