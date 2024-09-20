
package pro.ddsr.backendantiques.modules.tipo_persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.tipo_persona.repository.TipoPersonaRepository;
import pro.ddsr.backendantiques.modules.tipo_persona.entity.TipoPersona;

@Service
public class TipoPersonaService {
    @Autowired
    TipoPersonaRepository tipo_personaRepository;

    @Transactional
    public Optional<TipoPersona> delete(Long id) {
        Optional<TipoPersona> optionalTipoPersona = this.tipo_personaRepository.findById(id);
        optionalTipoPersona.ifPresent(
            TipoPersonaFound -> {
                this.tipo_personaRepository.delete(TipoPersonaFound);
            }
        );
        return optionalTipoPersona;
    }
 
    public List<TipoPersona> findAll() {
        return (List<TipoPersona>) this.tipo_personaRepository.findAll();
    }

    public Optional<TipoPersona> findById(Long id) {
        return this.tipo_personaRepository.findById(id);
    }

    public TipoPersona save(TipoPersona TipoPersona) {
        return this.tipo_personaRepository.save(TipoPersona);
    }

    public Optional<TipoPersona> update(Long id, TipoPersona tipo_persona) {
        Optional<TipoPersona> optionalTipoPersona = this.tipo_personaRepository.findById(id);
        if (optionalTipoPersona.isPresent()) {
            TipoPersona tipo_personaItem = optionalTipoPersona.orElseThrow();
            // Sets
            return Optional.of(this.tipo_personaRepository.save(tipo_personaItem));
        }
        return optionalTipoPersona;
    }
}
