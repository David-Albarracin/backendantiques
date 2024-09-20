
package pro.ddsr.backendantiques.modules.tipo_transaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.tipo_transaccion.entity.TipoTransaccion;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Long> {
    // Define repository methods here
}
