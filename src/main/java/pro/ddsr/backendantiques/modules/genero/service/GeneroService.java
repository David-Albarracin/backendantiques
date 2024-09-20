
package pro.ddsr.backendantiques.modules.genero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.genero.repository.GeneroRepository;
import pro.ddsr.backendantiques.modules.genero.entity.Genero;

@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    @Transactional
    public Optional<Genero> delete(Long id) {
        Optional<Genero> optionalGenero = this.generoRepository.findById(id);
        optionalGenero.ifPresent(
            GeneroFound -> {
                this.generoRepository.delete(GeneroFound);
            }
        );
        return optionalGenero;
    }
 
    public List<Genero> findAll() {
        return (List<Genero>) this.generoRepository.findAll();
    }

    public Optional<Genero> findById(Long id) {
        return this.generoRepository.findById(id);
    }

    public Genero save(Genero Genero) {
        return this.generoRepository.save(Genero);
    }

    public Optional<Genero> update(Long id, Genero genero) {
        Optional<Genero> optionalGenero = this.generoRepository.findById(id);
        if (optionalGenero.isPresent()) {
            Genero generoItem = optionalGenero.orElseThrow();
            // Sets
            return Optional.of(this.generoRepository.save(generoItem));
        }
        return optionalGenero;
    }
}
