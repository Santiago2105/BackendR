package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.MajorDTO;
import pe.edu.upc.backend.dtos.MajorReportDTO;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.entities.Major;
import pe.edu.upc.backend.repositories.MajorRepository;
import pe.edu.upc.backend.services.FacultyService;
import pe.edu.upc.backend.services.MajorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    FacultyService facultyService;

    @Override
    public MajorDTO add(MajorDTO majorDTO) {
        //Paso 1: Buscar los FK
        Faculty facultyFound = facultyService.findById(majorDTO.getFacultyId());
        if (facultyFound==null){
            return null;
        }

        //Paso 2: Validar campos

        if (majorDTO.getName()==null || majorDTO.getName().isBlank()) {
            return null;
        }

        //Paso 3: Grabar y actualizar respuesta

        Major major = new Major(null, majorDTO.getName(), facultyFound , null);
        major = majorRepository.save(major);
        majorDTO.setId(major.getId());
        return majorDTO;
    }

    @Override
    public Major findById(Long id) {
        return majorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Major> listAll() {
        return majorRepository.findAll();
    }

    @Override
    public List<Major> listByFacultyId(Long id) {
        return majorRepository.findByFaculty_Id(id);
    }

    @Override
    public List<MajorReportDTO> reportAll() {
        /// Posibilidad 1
        List<MajorReportDTO> majorReportDTOs= new ArrayList<>();
        List<Major> majors = listAll();

        for (Major major: majors){

            MajorReportDTO majorReportDTO = new MajorReportDTO(
                    major.getId(),
                    major.getName(),
                    major.getFaculty().getId(),
                    major.getFaculty().getName(),
                    major.getStudents().size()
            );
            majorReportDTOs.add(majorReportDTO);

        }
        return majorReportDTOs;
    }


}
