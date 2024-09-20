
package pro.ddsr.backendantiques.modules.paises.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.paises.repository.PaisesRepository;
import pro.ddsr.backendantiques.modules.paises.entity.Paises;

@Service
public class PaisesService {
    @Autowired
    PaisesRepository paisesRepository;

    @Transactional
    public Optional<Paises> delete(Long id) {
        Optional<Paises> optionalPaises = this.paisesRepository.findById(id);
        optionalPaises.ifPresent(
            PaisesFound -> {
                this.paisesRepository.delete(PaisesFound);
            }
        );
        return optionalPaises;
    }
 
    public List<Paises> findAll() {
        return (List<Paises>) this.paisesRepository.findAll();
    }

    public Optional<Paises> findById(Long id) {
        return this.paisesRepository.findById(id);
    }

    public Paises save(Paises Paises) {
        return this.paisesRepository.save(Paises);
    }

    public Optional<Paises> update(Long id, Paises paises) {
        Optional<Paises> optionalPaises = this.paisesRepository.findById(id);
        if (optionalPaises.isPresent()) {
            Paises paisesItem = optionalPaises.orElseThrow();
            // Sets
            return Optional.of(this.paisesRepository.save(paisesItem));
        }
        return optionalPaises;
    }
}
