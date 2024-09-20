
package pro.ddsr.backendantiques.modules.tipo_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.tipo_persona.entity.TipoPersona;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Long> {
    // Define repository methods here
}
