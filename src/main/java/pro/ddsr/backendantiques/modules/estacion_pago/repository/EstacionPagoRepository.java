
package pro.ddsr.backendantiques.modules.estacion_pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.estacion_pago.entity.EstacionPago;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionPagoRepository extends JpaRepository<EstacionPago, Long> {
    // Define repository methods here
}
