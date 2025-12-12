package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.ReporteDTO;
import pe.edu.upc.backend.repositories.CancionRepository;
import pe.edu.upc.backend.services.CancionService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CancionServiceImpl implements CancionService {
    @Autowired
    private CancionRepository cancionRepository;


    @Override
    public List<ReporteDTO> obtenerSumaDuracionPorAlbum() {
        // 1. Obtenemos la data cruda de la base de datos
        List<Object[]> resultados = cancionRepository.obtenerSumaDuracionPorAlbum();

        // 2. Preparamos la lista limpia
        List<ReporteDTO> listaDTO = new ArrayList<>();

        // 3. Convertimos fila por fila
        for (Object[] fila : resultados) {
            String nombre = (String) fila[0];
            Double total = (Double) fila[1];

            if(total == null) total = 0.0;

            // Creamos el objeto bonito
            listaDTO.add(new ReporteDTO(nombre, total));
        }

        return listaDTO;
    }
}
