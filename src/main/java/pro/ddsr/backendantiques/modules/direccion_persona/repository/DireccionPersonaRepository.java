
package pro.ddsr.backendantiques.modules.direccion_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.direccion_persona.entity.DireccionPersona;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionPersonaRepository extends JpaRepository<DireccionPersona, Long> {
    // Define repository methods here
}
