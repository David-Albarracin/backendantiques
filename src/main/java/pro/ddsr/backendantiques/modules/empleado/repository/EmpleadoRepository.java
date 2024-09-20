
package pro.ddsr.backendantiques.modules.empleado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.empleado.entity.Empleado;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Define repository methods here
}
