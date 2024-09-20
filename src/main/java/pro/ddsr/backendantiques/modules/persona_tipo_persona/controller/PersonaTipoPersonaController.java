
package pro.ddsr.backendantiques.modules.persona_tipo_persona.controller;

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

import pro.ddsr.backendantiques.modules.persona_tipo_persona.service.PersonaTipoPersonaService;
import pro.ddsr.backendantiques.modules.persona_tipo_persona.entity.PersonaTipoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/persona_tipo_persona")
public class PersonaTipoPersonaController {

    @Autowired
    private PersonaTipoPersonaService persona_tipo_personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<PersonaTipoPersona> listPersonaTipoPersona() {
        return this.persona_tipo_personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<PersonaTipoPersona> view(@PathVariable Long id) {
        Optional<PersonaTipoPersona> optionalPersonaTipoPersona = persona_tipo_personaService.findById(id);
        if (optionalPersonaTipoPersona.isPresent()) {
            return ResponseEntity.ok(optionalPersonaTipoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody PersonaTipoPersona persona_tipo_persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(persona_tipo_personaService.save(persona_tipo_persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<PersonaTipoPersona> update(@PathVariable Long id, @Valid @RequestBody PersonaTipoPersona persona_tipo_persona) {
        Optional<PersonaTipoPersona> persona_tipo_personaOptional = this.persona_tipo_personaService.update(id, persona_tipo_persona);
        if (persona_tipo_personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(persona_tipo_personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<PersonaTipoPersona> delete(@PathVariable Long id) {
        Optional<PersonaTipoPersona> optionalPersonaTipoPersona = this.persona_tipo_personaService.delete(id);
        if (optionalPersonaTipoPersona.isPresent()) {
            return ResponseEntity.ok(optionalPersonaTipoPersona.orElseThrow());
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
