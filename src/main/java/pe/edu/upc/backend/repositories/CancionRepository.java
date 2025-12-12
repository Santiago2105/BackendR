package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Cancion;
import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // SELECT album, SUM(duracion)
    // RECUERDA: Cambia 'jpTitulo' y 'jpDuracionMinutos' por TUS variables
    @Query("SELECT c.album.jpTitulo, SUM(c.jpDuracionMinutos) " +
            "FROM Cancion c GROUP BY c.album.jpTitulo")
    List<Object[]> obtenerSumaDuracionPorAlbum();
}