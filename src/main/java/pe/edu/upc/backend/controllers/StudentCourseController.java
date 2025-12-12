package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.StudentCourseDTO;
import pe.edu.upc.backend.entities.Course;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.services.StudentCourseService;

import java.util.List;

@CrossOrigin("*") //Lista de IPs que me pueden hacer peticiones
@RestController
@RequestMapping("/upc")
public class StudentCourseController {

    @Autowired
    StudentCourseService studentCourseService;


    @GetMapping("/students_courses/student/{studentId}")
    public ResponseEntity<List<StudentCourseDTO>> listByStundentId(@PathVariable("studentId") Long id){
        return new ResponseEntity<>(studentCourseService.listByStudentId(id), HttpStatus.OK);
    }

    @DeleteMapping("/students_courses/{id}") // http://localhost:8080/upc/studentscourses/5 -> Metodo DELETE
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        studentCourseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/students_courses") // http://localhost:8080/upc/studentscourses -> Metodo POST
    public ResponseEntity<StudentCourseDTO> add(@RequestBody StudentCourseDTO studentCourseDTO){
        StudentCourseDTO newStudentCourseDTO=studentCourseService.add(studentCourseDTO);
        return new ResponseEntity<>(newStudentCourseDTO, HttpStatus.CREATED);
    }

}
