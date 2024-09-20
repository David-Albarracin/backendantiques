
package pro.ddsr.backendantiques.modules.contacto_persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.contacto_persona.repository.ContactoPersonaRepository;
import pro.ddsr.backendantiques.modules.contacto_persona.entity.ContactoPersona;

@Service
public class ContactoPersonaService {
    @Autowired
    ContactoPersonaRepository contacto_personaRepository;

    @Transactional
    public Optional<ContactoPersona> delete(Long id) {
        Optional<ContactoPersona> optionalContactoPersona = this.contacto_personaRepository.findById(id);
        optionalContactoPersona.ifPresent(
            ContactoPersonaFound -> {
                this.contacto_personaRepository.delete(ContactoPersonaFound);
            }
        );
        return optionalContactoPersona;
    }
 
    public List<ContactoPersona> findAll() {
        return (List<ContactoPersona>) this.contacto_personaRepository.findAll();
    }

    public Optional<ContactoPersona> findById(Long id) {
        return this.contacto_personaRepository.findById(id);
    }

    public ContactoPersona save(ContactoPersona ContactoPersona) {
        return this.contacto_personaRepository.save(ContactoPersona);
    }

    public Optional<ContactoPersona> update(Long id, ContactoPersona contacto_persona) {
        Optional<ContactoPersona> optionalContactoPersona = this.contacto_personaRepository.findById(id);
        if (optionalContactoPersona.isPresent()) {
            ContactoPersona contacto_personaItem = optionalContactoPersona.orElseThrow();
            // Sets
            return Optional.of(this.contacto_personaRepository.save(contacto_personaItem));
        }
        return optionalContactoPersona;
    }
}
