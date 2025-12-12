package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String director;
    private boolean active;
    private LocalDate foundationDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] logo;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private List<Major> majors;

}
