
package pro.ddsr.backendantiques.modules.categorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backendantiques.modules.categorias.entity.Categorias;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    // Define repository methods here
}
