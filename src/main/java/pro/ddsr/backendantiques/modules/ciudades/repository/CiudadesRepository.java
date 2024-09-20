
package pro.ddsr.backendantiques.modules.ciudades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.ciudades.entity.Ciudades;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadesRepository extends JpaRepository<Ciudades, Long> {
    // Define repository methods here
}
