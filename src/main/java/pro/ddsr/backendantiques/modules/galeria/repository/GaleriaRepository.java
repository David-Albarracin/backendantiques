
package pro.ddsr.backendantiques.modules.galeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.galeria.entity.Galeria;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleriaRepository extends JpaRepository<Galeria, Long> {
    // Define repository methods here
}
