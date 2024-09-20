
package pro.ddsr.backendantiques.modules.coleccionistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.coleccionistas.entity.Coleccionistas;
import org.springframework.stereotype.Repository;

@Repository
public interface ColeccionistasRepository extends JpaRepository<Coleccionistas, Long> {
    // Define repository methods here
}
