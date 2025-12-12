package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.StudentDTO;
import pe.edu.upc.backend.entities.Major;
import pe.edu.upc.backend.entities.Student;
import pe.edu.upc.backend.exceptions.RequiredDataException;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.StudentRepository;
import pe.edu.upc.backend.services.MajorService;
import pe.edu.upc.backend.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MajorService majorService;

    public List<StudentDTO> listAll() {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        List<Student> students=studentRepository.findAll();

        for (Student student: students) {
            studentDTOs.add(
                    new StudentDTO(student.getId(), student.getName(), student.getCity(),
                            student.getCredits(), student.getMajor().getId(), student.getMajor().getName())
            );
        }
        return studentDTOs;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public StudentDTO findByIdDTO(Long id) {
        Student studentFound = findById(id);
        if (studentFound!=null) {
            return new StudentDTO(studentFound.getId(),studentFound.getName(),
                    studentFound.getCity(),studentFound.getCredits(),studentFound.getMajor().getId(),
                    studentFound.getMajor().getName());
        }
        return null;

    }

    public StudentDTO add(StudentDTO studentDTO) {
        Major major= majorService.findById(studentDTO.getMajorId());
        if (major==null) {
            throw new ResourceNotFoundException("Major with id: "+studentDTO.getMajorId()+ " can not be found");
        }

        if(studentDTO.getName()==null || studentDTO.getName().isBlank()) {
            throw new RequiredDataException("Student Name can not be null or blank");
        }

        if(studentDTO.getCity()==null || studentDTO.getCity().isBlank()) {
            throw new RequiredDataException("Student City can not be null or blank");
        }


        Student newStudent = new Student(null,studentDTO.getName(), studentDTO.getCity(),
                studentDTO.getCredits(),major,null);

        newStudent=studentRepository.save(newStudent);
        studentDTO.setId(newStudent.getId());
        studentDTO.setMajorName(major.getName());
        return studentDTO;
    }




}
