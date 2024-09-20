
package pro.ddsr.backendantiques.modules.estado_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.estado_persona.entity.EstadoPersona;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPersonaRepository extends JpaRepository<EstadoPersona, Long> {
    // Define repository methods here
}
