
package pro.ddsr.backendantiques.modules.contacto_persona.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.ddsr.backendantiques.modules.contacto_persona.service.ContactoPersonaService;
import pro.ddsr.backendantiques.modules.contacto_persona.entity.ContactoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacto_persona")
public class ContactoPersonaController {

    @Autowired
    private ContactoPersonaService contacto_personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ContactoPersona> listContactoPersona() {
        return this.contacto_personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ContactoPersona> view(@PathVariable Long id) {
        Optional<ContactoPersona> optionalContactoPersona = contacto_personaService.findById(id);
        if (optionalContactoPersona.isPresent()) {
            return ResponseEntity.ok(optionalContactoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody ContactoPersona contacto_persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contacto_personaService.save(contacto_persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ContactoPersona> update(@PathVariable Long id, @Valid @RequestBody ContactoPersona contacto_persona) {
        Optional<ContactoPersona> contacto_personaOptional = this.contacto_personaService.update(id, contacto_persona);
        if (contacto_personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(contacto_personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ContactoPersona> delete(@PathVariable Long id) {
        Optional<ContactoPersona> optionalContactoPersona = this.contacto_personaService.delete(id);
        if (optionalContactoPersona.isPresent()) {
            return ResponseEntity.ok(optionalContactoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
