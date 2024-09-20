
package pro.ddsr.backendantiques.modules.mov_caja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.mov_caja.entity.MovCaja;
import org.springframework.stereotype.Repository;

@Repository
public interface MovCajaRepository extends JpaRepository<MovCaja, Long> {
    // Define repository methods here
}
