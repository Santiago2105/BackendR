package pe.edu.upc.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorReportDTO {

    private Long id;
    private String name;
    private Long facultyId;
    private String facultyName;
    private Integer studentsCount;


}
