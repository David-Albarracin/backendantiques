
package pro.ddsr.backendantiques.modules.epoca_antiguedad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.epoca_antiguedad.entity.EpocaAntiguedad;
import org.springframework.stereotype.Repository;

@Repository
public interface EpocaAntiguedadRepository extends JpaRepository<EpocaAntiguedad, Long> {
    // Define repository methods here
}
