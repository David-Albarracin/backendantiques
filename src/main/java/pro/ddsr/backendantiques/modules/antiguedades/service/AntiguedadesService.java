
package pro.ddsr.backendantiques.modules.antiguedades.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.antiguedades.repository.AntiguedadesRepository;
import pro.ddsr.backendantiques.modules.antiguedades.entity.Antiguedades;

@Service
public class AntiguedadesService {
    @Autowired
    AntiguedadesRepository antiguedadesRepository;

    @Transactional
    public Optional<Antiguedades> delete(Long id) {
        Optional<Antiguedades> optionalAntiguedades = this.antiguedadesRepository.findById(id);
        optionalAntiguedades.ifPresent(
            AntiguedadesFound -> {
                this.antiguedadesRepository.delete(AntiguedadesFound);
            }
        );
        return optionalAntiguedades;
    }
 
    public List<Antiguedades> findAll() {
        return (List<Antiguedades>) this.antiguedadesRepository.findAll();
    }

    public Optional<Antiguedades> findById(Long id) {
        return this.antiguedadesRepository.findById(id);
    }

    public Antiguedades save(Antiguedades Antiguedades) {
        return this.antiguedadesRepository.save(Antiguedades);
    }

    public Optional<Antiguedades> update(Long id, Antiguedades antiguedades) {
        Optional<Antiguedades> optionalAntiguedades = this.antiguedadesRepository.findById(id);
        if (optionalAntiguedades.isPresent()) {
            Antiguedades antiguedadesItem = optionalAntiguedades.orElseThrow();
            // Sets
            return Optional.of(this.antiguedadesRepository.save(antiguedadesItem));
        }
        return optionalAntiguedades;
    }
}
