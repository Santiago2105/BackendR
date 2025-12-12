package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.MajorDTO;
import pe.edu.upc.backend.dtos.MajorReportDTO;
import pe.edu.upc.backend.entities.Major;

import java.util.List;

public interface MajorService {

    public MajorDTO add(MajorDTO majorDTO);
    public Major findById(Long id);
    public List<Major> listAll();
    public List<Major> listByFacultyId(Long id);

    public List<MajorReportDTO> reportAll();

}
