
package pro.ddsr.backendantiques.modules.categorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.categorias.repository.CategoriasRepository;
import pro.ddsr.backendantiques.modules.categorias.entity.Categorias;

@Service
public class CategoriasService {
    @Autowired
    CategoriasRepository categoriasRepository;

    @Transactional
    public Optional<Categorias> delete(Long id) {
        Optional<Categorias> optionalCategorias = this.categoriasRepository.findById(id);
        optionalCategorias.ifPresent(
            CategoriasFound -> {
                this.categoriasRepository.delete(CategoriasFound);
            }
        );
        return optionalCategorias;
    }
 
    public List<Categorias> findAll() {
        return (List<Categorias>) this.categoriasRepository.findAll();
    }

    public Optional<Categorias> findById(Long id) {
        return this.categoriasRepository.findById(id);
    }

    public Categorias save(Categorias Categorias) {
        return this.categoriasRepository.save(Categorias);
    }

    public Optional<Categorias> update(Long id, Categorias categorias) {
        Optional<Categorias> optionalCategorias = this.categoriasRepository.findById(id);
        if (optionalCategorias.isPresent()) {
            Categorias categoriasItem = optionalCategorias.orElseThrow();
            // Sets
            return Optional.of(this.categoriasRepository.save(categoriasItem));
        }
        return optionalCategorias;
    }
}
