package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REGLA: Variables con tus iniciales
    @Column(nullable = false)
    private String jpTitulo;

    @Column(nullable = false)
    private String jpArtista;

    @Column(nullable = false)
    private int jpAnio;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cancion> canciones;
}