package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jpTitulo;

    // Importante para el cálculo matemático
    @Column(name = "duracion_minutos", nullable = false)
    private double jpDuracionMinutos;

    @ToString.Exclude
    @JsonIgnore // Evita bucles al serializar
    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;
}