
package pro.ddsr.backendantiques.modules.direccion_persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.direccion_persona.repository.DireccionPersonaRepository;
import pro.ddsr.backendantiques.modules.direccion_persona.entity.DireccionPersona;

@Service
public class DireccionPersonaService {
    @Autowired
    DireccionPersonaRepository direccion_personaRepository;

    @Transactional
    public Optional<DireccionPersona> delete(Long id) {
        Optional<DireccionPersona> optionalDireccionPersona = this.direccion_personaRepository.findById(id);
        optionalDireccionPersona.ifPresent(
            DireccionPersonaFound -> {
                this.direccion_personaRepository.delete(DireccionPersonaFound);
            }
        );
        return optionalDireccionPersona;
    }
 
    public List<DireccionPersona> findAll() {
        return (List<DireccionPersona>) this.direccion_personaRepository.findAll();
    }

    public Optional<DireccionPersona> findById(Long id) {
        return this.direccion_personaRepository.findById(id);
    }

    public DireccionPersona save(DireccionPersona DireccionPersona) {
        return this.direccion_personaRepository.save(DireccionPersona);
    }

    public Optional<DireccionPersona> update(Long id, DireccionPersona direccion_persona) {
        Optional<DireccionPersona> optionalDireccionPersona = this.direccion_personaRepository.findById(id);
        if (optionalDireccionPersona.isPresent()) {
            DireccionPersona direccion_personaItem = optionalDireccionPersona.orElseThrow();
            // Sets
            return Optional.of(this.direccion_personaRepository.save(direccion_personaItem));
        }
        return optionalDireccionPersona;
    }
}
