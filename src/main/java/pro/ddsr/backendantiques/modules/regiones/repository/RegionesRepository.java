
package pro.ddsr.backendantiques.modules.regiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.regiones.entity.Regiones;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionesRepository extends JpaRepository<Regiones, Long> {
    // Define repository methods here
}
