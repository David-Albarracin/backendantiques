
package pro.ddsr.backendantiques.modules.transacciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.transacciones.entity.Transacciones;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionesRepository extends JpaRepository<Transacciones, Long> {
    // Define repository methods here
}
