
package pro.ddsr.backendantiques.modules.paises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.paises.entity.Paises;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisesRepository extends JpaRepository<Paises, Long> {
    // Define repository methods here
}
