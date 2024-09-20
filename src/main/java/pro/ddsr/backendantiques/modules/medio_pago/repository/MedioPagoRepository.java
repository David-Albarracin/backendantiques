
package pro.ddsr.backendantiques.modules.medio_pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.medio_pago.entity.MedioPago;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {
    // Define repository methods here
}
