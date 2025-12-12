package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.MajorDTO;
import pe.edu.upc.backend.dtos.MajorReportDTO;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.entities.Major;
import pe.edu.upc.backend.services.MajorService;

import java.util.List;

@CrossOrigin("*") //Lista de IPs que me pueden hacer peticiones
@RestController
@RequestMapping("/upc")   // http://localhost:8080/upc
public class MajorController {

    @Autowired
    MajorService majorService;


    @GetMapping("/majors/report") // http://localhost:8080/upc/majors/report -> Metodo GET
    public ResponseEntity<List<MajorReportDTO>> reportAll(){
        return new ResponseEntity<>(majorService.reportAll(), HttpStatus.OK);
    }


    @GetMapping("/majors") // http://localhost:8080/upc/majors -> Metodo GET
    public ResponseEntity<List<Major>> listAll(){
        return new ResponseEntity<>(majorService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/majors/{id}") // http://localhost:8080/upc/majors/1 -> Metodo GET
    public ResponseEntity<Major> findByMajorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(majorService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/majors/faculty/{id}") // http://localhost:8080/upc/majors/faculty/1 -> Metodo GET
    public ResponseEntity<List<Major>> listAll(@PathVariable("id") Long id){
        List<Major> majors =  majorService.listByFacultyId(id);
        return new ResponseEntity<>(majors, HttpStatus.OK);
    }

    @PostMapping("/majors") // http://localhost:8080/upc/majors -> Metodo POST
    public ResponseEntity<MajorDTO> add(@RequestBody MajorDTO majorDTO){
        majorDTO=majorService.add(majorDTO);
        return new ResponseEntity<>(majorDTO, HttpStatus.CREATED);
    }

}
