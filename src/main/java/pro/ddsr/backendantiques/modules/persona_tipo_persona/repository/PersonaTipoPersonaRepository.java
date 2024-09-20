
package pro.ddsr.backendantiques.modules.persona_tipo_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.persona_tipo_persona.entity.PersonaTipoPersona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaTipoPersonaRepository extends JpaRepository<PersonaTipoPersona, Long> {
    // Define repository methods here
}
