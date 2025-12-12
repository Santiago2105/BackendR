package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.backend.dtos.FacultySummaryDTO;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.services.FacultyService;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*") //Lista de IPs que me pueden hacer peticiones
@RestController
@RequestMapping("/upc")   // http://localhost:8080/upc
public class FacultyController {

    @Autowired
    FacultyService facultyService;


    @PutMapping(value="/faculties/logo/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Faculty> updateLogo(@PathVariable("id") Long id,
                                              @RequestPart(name="logo") MultipartFile logo) throws IOException {
            Faculty faculty = facultyService.updateLogo(id,logo.getBytes());
            if (faculty!=null) {
                return new ResponseEntity<>(faculty, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping("/faculties/summary") // http://localhost:8080/upc/faculties -> Metodo GET
    public ResponseEntity<List<FacultySummaryDTO>> summaryList(){
        return new ResponseEntity<>(facultyService.summaryList(),HttpStatus.OK);
    }


    @GetMapping("/faculties") // http://localhost:8080/upc/faculties -> Metodo GET
    public ResponseEntity<List<Faculty>> listAll(){
        return new ResponseEntity<>(facultyService.listAll(),HttpStatus.OK);
    }

    @PostMapping("/faculties") // http://localhost:8080/upc/faculties -> Metodo POST
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty){
        Faculty newFaculty=facultyService.add(faculty);
        return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
    }

    @DeleteMapping("/faculties/{id}") // http://localhost:8080/upc/faculties/5 -> Metodo DELETE
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        facultyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/faculties/{id}") // http://localhost:8080/upc/faculties/5 -> Metodo GET
    public ResponseEntity<Faculty> findById(@PathVariable("id") Long id){
        return new ResponseEntity<Faculty>(facultyService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/faculties")
    public ResponseEntity<Faculty> edit(@RequestBody Faculty faculty){
        Faculty facultyUpdated = facultyService.edit(faculty);
        return new ResponseEntity<Faculty>(facultyUpdated,HttpStatus.OK);
    }


}
