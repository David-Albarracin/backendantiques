
package pro.ddsr.backendantiques.modules.tipom_caja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.tipom_caja.entity.TipomCaja;
import org.springframework.stereotype.Repository;

@Repository
public interface TipomCajaRepository extends JpaRepository<TipomCaja, Long> {
    // Define repository methods here
}
