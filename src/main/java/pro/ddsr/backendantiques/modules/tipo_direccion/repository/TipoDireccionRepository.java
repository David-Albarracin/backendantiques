
package pro.ddsr.backendantiques.modules.tipo_direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.tipo_direccion.entity.TipoDireccion;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDireccionRepository extends JpaRepository<TipoDireccion, Long> {
    // Define repository methods here
}
