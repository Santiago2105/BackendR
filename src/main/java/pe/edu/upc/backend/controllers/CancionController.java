package pe.edu.upc.backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // No olvides importar esto
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.ReporteDTO;
import pe.edu.upc.backend.services.CancionService;

import java.util.List;

@CrossOrigin("*")
@RestController
// 1. CORRECCIÓN DE RUTA (Obligatorio según PDF)
@RequestMapping("/upc/martinez/promedios")
public class CancionController {

    @Autowired
    CancionService cancionService;

    // 2. CORRECCIÓN DE SEGURIDAD (Obligatorio para puntaje de Backend)
    @PreAuthorize("hasAuthority('ROLE_PRODUCTOR')")
    @GetMapping
    public List<ReporteDTO> verReporte() {
        return cancionService.obtenerSumaDuracionPorAlbum();
    }
}