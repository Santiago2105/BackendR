package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.FacultySummaryDTO;
import pe.edu.upc.backend.entities.Faculty;

import java.util.List;

public interface FacultyService {

    public Faculty add(Faculty faculty);

    public void delete(Long id);

    public Faculty findById(Long id);

    public List<Faculty> listAll();

    public Faculty edit(Faculty faculty);

    public Faculty updateLogo(Long id, byte[] logo);

    public List<FacultySummaryDTO> summaryList();

}
