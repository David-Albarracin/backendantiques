
package pro.ddsr.backendantiques.modules.contacto_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.contacto_persona.entity.ContactoPersona;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoPersonaRepository extends JpaRepository<ContactoPersona, Long> {
    // Define repository methods here
}
