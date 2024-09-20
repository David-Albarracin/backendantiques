
package pro.ddsr.backendantiques.modules.estado_persona.controller;

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

import pro.ddsr.backendantiques.modules.estado_persona.service.EstadoPersonaService;
import pro.ddsr.backendantiques.modules.estado_persona.entity.EstadoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado_persona")
public class EstadoPersonaController {

    @Autowired
    private EstadoPersonaService estado_personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<EstadoPersona> listEstadoPersona() {
        return this.estado_personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<EstadoPersona> view(@PathVariable Long id) {
        Optional<EstadoPersona> optionalEstadoPersona = estado_personaService.findById(id);
        if (optionalEstadoPersona.isPresent()) {
            return ResponseEntity.ok(optionalEstadoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody EstadoPersona estado_persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estado_personaService.save(estado_persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<EstadoPersona> update(@PathVariable Long id, @Valid @RequestBody EstadoPersona estado_persona) {
        Optional<EstadoPersona> estado_personaOptional = this.estado_personaService.update(id, estado_persona);
        if (estado_personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estado_personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<EstadoPersona> delete(@PathVariable Long id) {
        Optional<EstadoPersona> optionalEstadoPersona = this.estado_personaService.delete(id);
        if (optionalEstadoPersona.isPresent()) {
            return ResponseEntity.ok(optionalEstadoPersona.orElseThrow());
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
