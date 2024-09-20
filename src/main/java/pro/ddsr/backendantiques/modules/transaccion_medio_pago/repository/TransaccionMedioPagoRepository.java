
package pro.ddsr.backendantiques.modules.transaccion_medio_pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.transaccion_medio_pago.entity.TransaccionMedioPago;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionMedioPagoRepository extends JpaRepository<TransaccionMedioPago, Long> {
    // Define repository methods here
}
