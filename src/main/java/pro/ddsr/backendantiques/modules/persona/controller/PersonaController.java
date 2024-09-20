
package pro.ddsr.backendantiques.modules.persona.controller;

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

import pro.ddsr.backendantiques.modules.persona.service.PersonaService;
import pro.ddsr.backendantiques.modules.persona.entity.Persona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Persona> listPersona() {
        return this.personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Persona> view(@PathVariable Long id) {
        Optional<Persona> optionalPersona = personaService.findById(id);
        if (optionalPersona.isPresent()) {
            return ResponseEntity.ok(optionalPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Persona> update(@PathVariable Long id, @Valid @RequestBody Persona persona) {
        Optional<Persona> personaOptional = this.personaService.update(id, persona);
        if (personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Persona> delete(@PathVariable Long id) {
        Optional<Persona> optionalPersona = this.personaService.delete(id);
        if (optionalPersona.isPresent()) {
            return ResponseEntity.ok(optionalPersona.orElseThrow());
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
