package pe.edu.upc.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private String nombreAlbum;
    private double duracionTotal;
}