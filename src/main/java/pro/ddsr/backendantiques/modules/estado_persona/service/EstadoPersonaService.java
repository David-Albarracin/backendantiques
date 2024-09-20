
package pro.ddsr.backendantiques.modules.estado_persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.estado_persona.repository.EstadoPersonaRepository;
import pro.ddsr.backendantiques.modules.estado_persona.entity.EstadoPersona;

@Service
public class EstadoPersonaService {
    @Autowired
    EstadoPersonaRepository estado_personaRepository;

    @Transactional
    public Optional<EstadoPersona> delete(Long id) {
        Optional<EstadoPersona> optionalEstadoPersona = this.estado_personaRepository.findById(id);
        optionalEstadoPersona.ifPresent(
            EstadoPersonaFound -> {
                this.estado_personaRepository.delete(EstadoPersonaFound);
            }
        );
        return optionalEstadoPersona;
    }
 
    public List<EstadoPersona> findAll() {
        return (List<EstadoPersona>) this.estado_personaRepository.findAll();
    }

    public Optional<EstadoPersona> findById(Long id) {
        return this.estado_personaRepository.findById(id);
    }

    public EstadoPersona save(EstadoPersona EstadoPersona) {
        return this.estado_personaRepository.save(EstadoPersona);
    }

    public Optional<EstadoPersona> update(Long id, EstadoPersona estado_persona) {
        Optional<EstadoPersona> optionalEstadoPersona = this.estado_personaRepository.findById(id);
        if (optionalEstadoPersona.isPresent()) {
            EstadoPersona estado_personaItem = optionalEstadoPersona.orElseThrow();
            // Sets
            return Optional.of(this.estado_personaRepository.save(estado_personaItem));
        }
        return optionalEstadoPersona;
    }
}
