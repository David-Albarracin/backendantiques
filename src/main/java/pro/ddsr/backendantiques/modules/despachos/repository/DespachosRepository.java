
package pro.ddsr.backendantiques.modules.despachos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.despachos.entity.Despachos;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachosRepository extends JpaRepository<Despachos, Long> {
    // Define repository methods here
}
