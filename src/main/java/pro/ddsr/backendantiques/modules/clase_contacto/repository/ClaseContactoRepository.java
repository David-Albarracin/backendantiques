
package pro.ddsr.backendantiques.modules.clase_contacto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.clase_contacto.entity.ClaseContacto;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseContactoRepository extends JpaRepository<ClaseContacto, Long> {
    // Define repository methods here
}
