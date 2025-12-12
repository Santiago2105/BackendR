package pe.edu.upc.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.backend.entities.Course;
import pe.edu.upc.backend.entities.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDTO {
    private Long id;

    private Long courseId;
    private String courseName;
    private Long studentId;
    private String studentName;
    private String academicTerm; //2025-10, 2025-20
    private Double grade;

}
