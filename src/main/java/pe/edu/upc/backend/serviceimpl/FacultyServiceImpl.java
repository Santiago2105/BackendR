package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.FacultySummaryDTO;
import pe.edu.upc.backend.entities.Faculty;
import pe.edu.upc.backend.exceptions.InvalidActionException;
import pe.edu.upc.backend.exceptions.KeyRepeatedDataException;
import pe.edu.upc.backend.exceptions.RequiredDataException;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.FacultyRepository;
import pe.edu.upc.backend.services.FacultyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public Faculty add(Faculty faculty) {

        /*
        Pasos:
        1. Validar que los campos cumplan los requisitos
        2. Interactuar con la BD para insertar el nuevo Faculty
         */

        if(faculty.getName()==null || faculty.getName().isBlank()) {
            throw new RequiredDataException("Faculty Name can not be null or blank");
        }
        if(faculty.getDirector()==null || faculty.getDirector().isBlank()) {
            throw new RequiredDataException("Faculty Director can not be null or blank");
        }

        Faculty foundFaculty = facultyRepository.findByName(faculty.getName());
        if(foundFaculty!=null) {
            throw new KeyRepeatedDataException("Faculty with name: "+ faculty.getName()+ " is repeated");
        }

        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        Faculty foundFaculty = findById(id);
        if (foundFaculty==null){
            throw new ResourceNotFoundException("Faculty id: "+id+" can not be found");
        }
        if (!foundFaculty.getMajors().isEmpty()) {
            throw new InvalidActionException("Faculty id: "+id+" has FK dependencies");
        }
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Faculty> listAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty edit(Faculty faculty) {

        Faculty facultyFound = findById(faculty.getId());
        if (facultyFound==null){
            return null;
        }
        if(faculty.getName()!=null && !faculty.getName().isBlank()) {
            if(facultyRepository.findByName(faculty.getName())==null) {
                facultyFound.setName(faculty.getName());
            }
        }
        if(faculty.getDirector()!=null && !faculty.getDirector().isBlank()) {
            facultyFound.setDirector(faculty.getDirector());
        }
        if(faculty.getFoundationDate()!=null) {
            facultyFound.setFoundationDate(faculty.getFoundationDate());
        }

        facultyFound.setActive(faculty.isActive());

        return facultyRepository.save(facultyFound);
    }

    @Override
    public Faculty updateLogo(Long id, byte[] logo) {
        Faculty facultyFound = findById(id);
        if (facultyFound==null){
            throw new ResourceNotFoundException("Faculty id: "+id+" can not be found");
        }
        facultyFound.setLogo(logo);
        return facultyRepository.save(facultyFound);
    }

    @Override
    public List<FacultySummaryDTO> summaryList() {
        List<FacultySummaryDTO> facultySummaryDTOList = new ArrayList<>();
        List<Faculty> facultyList = listAll();
        for (Faculty faculty : facultyList) {
            FacultySummaryDTO facultySummaryDTO = new FacultySummaryDTO(
                    faculty.getId(),
                    faculty.getName(),
                    faculty.getMajors().size()
            );
            facultySummaryDTOList.add(facultySummaryDTO);
        }


        return facultySummaryDTOList;
    }


}
