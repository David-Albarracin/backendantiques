
package pro.ddsr.backendantiques.modules.detalle_transaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.detalle_transaccion.entity.DetalleTransaccion;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleTransaccionRepository extends JpaRepository<DetalleTransaccion, Long> {
    // Define repository methods here
}
