package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.StudentCourseDTO;
import pe.edu.upc.backend.entities.Course;
import pe.edu.upc.backend.entities.Student;
import pe.edu.upc.backend.entities.StudentCourse;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.StudentCourseRepository;
import pe.edu.upc.backend.services.CourseService;
import pe.edu.upc.backend.services.StudentCourseService;
import pe.edu.upc.backend.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Override
    public StudentCourseDTO add(StudentCourseDTO studentCourseDTO) {

        Student student = studentService.findById(studentCourseDTO.getStudentId());
        Course course = courseService.findById(studentCourseDTO.getCourseId());

        if (student==null) {
            throw new ResourceNotFoundException("Student with id: "+studentCourseDTO.getStudentId()+" can not be found");
        }
        if (course==null) {
            throw new ResourceNotFoundException("Course with id: "+studentCourseDTO.getCourseId()+" can not be found");
        }

        StudentCourse studentCourse = new StudentCourse(null,course,student,studentCourseDTO.getAcademicTerm(),studentCourseDTO.getGrade());
        studentCourse = studentCourseRepository.save(studentCourse);
        studentCourseDTO.setId(studentCourse.getId());
        studentCourseDTO.setCourseName(course.getName());
        studentCourseDTO.setStudentName(student.getName());
        return studentCourseDTO;
    }

    @Override
    public List<StudentCourse> listAll() {
        return studentCourseRepository.findAll();
    }

    @Override
    public List<StudentCourseDTO> listByStudentId(Long id) {
        List<StudentCourseDTO> studentCourseDTOList = new ArrayList<>();
        List<StudentCourse> studentCourseList = studentCourseRepository.findByStudentId(id);
        for(StudentCourse studentCourse: studentCourseList ) {
            studentCourseDTOList.add(new StudentCourseDTO(
                    studentCourse.getId(),studentCourse.getCourse().getId(),studentCourse.getCourse().getName(),
                    studentCourse.getStudent().getId(),studentCourse.getStudent().getName(),
                    studentCourse.getAcademicTerm(),studentCourse.getGrade()
            ));
        }

        return studentCourseDTOList;
    }

    @Override
    public void delete(Long id) {
        StudentCourse studentCourseFound = findById(id);
        if (studentCourseFound==null) {
            throw new ResourceNotFoundException("StudentCourse with id: "+id+" can not be found");
        }
        studentCourseRepository.delete(studentCourseFound);
    }

    @Override
    public StudentCourse findById(Long id) {
        return studentCourseRepository.findById(id).orElse(null);
    }
}
