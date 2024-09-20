
package pro.ddsr.backendantiques.modules.historial_propiedad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.historial_propiedad.entity.HistorialPropiedad;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialPropiedadRepository extends JpaRepository<HistorialPropiedad, Long> {
    // Define repository methods here
}
