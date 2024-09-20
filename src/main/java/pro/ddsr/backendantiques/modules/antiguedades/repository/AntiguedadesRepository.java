
package pro.ddsr.backendantiques.modules.antiguedades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.antiguedades.entity.Antiguedades;
import org.springframework.stereotype.Repository;

@Repository
public interface AntiguedadesRepository extends JpaRepository<Antiguedades, Long> {
    // Define repository methods here
}
