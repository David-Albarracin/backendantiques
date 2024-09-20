
package pro.ddsr.backendantiques.modules.ranking_antiguedad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.ranking_antiguedad.entity.RankingAntiguedad;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingAntiguedadRepository extends JpaRepository<RankingAntiguedad, Long> {
    // Define repository methods here
}
