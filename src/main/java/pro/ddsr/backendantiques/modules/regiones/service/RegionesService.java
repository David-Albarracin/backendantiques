
package pro.ddsr.backendantiques.modules.regiones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.regiones.repository.RegionesRepository;
import pro.ddsr.backendantiques.modules.regiones.entity.Regiones;

@Service
public class RegionesService {
    @Autowired
    RegionesRepository regionesRepository;

    @Transactional
    public Optional<Regiones> delete(Long id) {
        Optional<Regiones> optionalRegiones = this.regionesRepository.findById(id);
        optionalRegiones.ifPresent(
            RegionesFound -> {
                this.regionesRepository.delete(RegionesFound);
            }
        );
        return optionalRegiones;
    }
 
    public List<Regiones> findAll() {
        return (List<Regiones>) this.regionesRepository.findAll();
    }

    public Optional<Regiones> findById(Long id) {
        return this.regionesRepository.findById(id);
    }

    public Regiones save(Regiones Regiones) {
        return this.regionesRepository.save(Regiones);
    }

    public Optional<Regiones> update(Long id, Regiones regiones) {
        Optional<Regiones> optionalRegiones = this.regionesRepository.findById(id);
        if (optionalRegiones.isPresent()) {
            Regiones regionesItem = optionalRegiones.orElseThrow();
            // Sets
            return Optional.of(this.regionesRepository.save(regionesItem));
        }
        return optionalRegiones;
    }
}
