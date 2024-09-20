
package pro.ddsr.backendantiques.modules.sucursal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.sucursal.entity.Sucursal;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    // Define repository methods here
}
