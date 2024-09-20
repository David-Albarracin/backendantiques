
package pro.ddsr.backendantiques.modules.persona_tipo_persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.persona_tipo_persona.repository.PersonaTipoPersonaRepository;
import pro.ddsr.backendantiques.modules.persona_tipo_persona.entity.PersonaTipoPersona;

@Service
public class PersonaTipoPersonaService {
    @Autowired
    PersonaTipoPersonaRepository persona_tipo_personaRepository;

    @Transactional
    public Optional<PersonaTipoPersona> delete(Long id) {
        Optional<PersonaTipoPersona> optionalPersonaTipoPersona = this.persona_tipo_personaRepository.findById(id);
        optionalPersonaTipoPersona.ifPresent(
            PersonaTipoPersonaFound -> {
                this.persona_tipo_personaRepository.delete(PersonaTipoPersonaFound);
            }
        );
        return optionalPersonaTipoPersona;
    }
 
    public List<PersonaTipoPersona> findAll() {
        return (List<PersonaTipoPersona>) this.persona_tipo_personaRepository.findAll();
    }

    public Optional<PersonaTipoPersona> findById(Long id) {
        return this.persona_tipo_personaRepository.findById(id);
    }

    public PersonaTipoPersona save(PersonaTipoPersona PersonaTipoPersona) {
        return this.persona_tipo_personaRepository.save(PersonaTipoPersona);
    }

    public Optional<PersonaTipoPersona> update(Long id, PersonaTipoPersona persona_tipo_persona) {
        Optional<PersonaTipoPersona> optionalPersonaTipoPersona = this.persona_tipo_personaRepository.findById(id);
        if (optionalPersonaTipoPersona.isPresent()) {
            PersonaTipoPersona persona_tipo_personaItem = optionalPersonaTipoPersona.orElseThrow();
            // Sets
            return Optional.of(this.persona_tipo_personaRepository.save(persona_tipo_personaItem));
        }
        return optionalPersonaTipoPersona;
    }
}
