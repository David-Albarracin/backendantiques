
package pro.ddsr.backendantiques.modules.persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.persona.repository.PersonaRepository;
import pro.ddsr.backendantiques.modules.persona.entity.Persona;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Transactional
    public Optional<Persona> delete(Long id) {
        Optional<Persona> optionalPersona = this.personaRepository.findById(id);
        optionalPersona.ifPresent(
            PersonaFound -> {
                this.personaRepository.delete(PersonaFound);
            }
        );
        return optionalPersona;
    }
 
    public List<Persona> findAll() {
        return (List<Persona>) this.personaRepository.findAll();
    }

    public Optional<Persona> findById(Long id) {
        return this.personaRepository.findById(id);
    }

    public Persona save(Persona Persona) {
        return this.personaRepository.save(Persona);
    }

    public Optional<Persona> update(Long id, Persona persona) {
        Optional<Persona> optionalPersona = this.personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona personaItem = optionalPersona.orElseThrow();
            // Sets
            return Optional.of(this.personaRepository.save(personaItem));
        }
        return optionalPersona;
    }
}
