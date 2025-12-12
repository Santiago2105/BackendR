package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.StudentDTO;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.services.StudentService;

import java.util.List;

@CrossOrigin("*") //Lista de IPs que me pueden hacer peticiones
@RestController
@RequestMapping("/upc")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students") // http://localhost:8080/upc/students -> Metodo GET
    public ResponseEntity<List<StudentDTO>> listAll(){
        return new ResponseEntity<>(studentService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}") // http://localhost:8080/upc/students -> Metodo GET
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.findByIdDTO(id), HttpStatus.OK);
    }

    @PostMapping("/students") // http://localhost:8080/upc/students -> Metodo POST
    public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO studentDTO){
        StudentDTO newStudentDTO=studentService.add(studentDTO);
        return new ResponseEntity<>(newStudentDTO, HttpStatus.CREATED);
    }



}
