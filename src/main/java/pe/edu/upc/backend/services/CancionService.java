package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.ReporteDTO;
import java.util.List;

public interface CancionService {
    List<ReporteDTO> obtenerSumaDuracionPorAlbum();
}