package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.backend.entities.Course;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.services.CourseService;

import java.util.List;

@CrossOrigin("*") //Lista de IPs que me pueden hacer peticiones
@RestController
@RequestMapping("/upc")   // http://localhost:8080/upc
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses") // http://localhost:8080/upc/courses -> Metodo GET
    public ResponseEntity<List<Course>> listAll(){
        return new ResponseEntity<>(courseService.listAll(), HttpStatus.OK);
    }


}
