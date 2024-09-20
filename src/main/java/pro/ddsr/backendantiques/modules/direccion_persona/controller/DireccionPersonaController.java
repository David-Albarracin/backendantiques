
package pro.ddsr.backendantiques.modules.direccion_persona.controller;

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

import pro.ddsr.backendantiques.modules.direccion_persona.service.DireccionPersonaService;
import pro.ddsr.backendantiques.modules.direccion_persona.entity.DireccionPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/direccion_persona")
public class DireccionPersonaController {

    @Autowired
    private DireccionPersonaService direccion_personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<DireccionPersona> listDireccionPersona() {
        return this.direccion_personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DireccionPersona> view(@PathVariable Long id) {
        Optional<DireccionPersona> optionalDireccionPersona = direccion_personaService.findById(id);
        if (optionalDireccionPersona.isPresent()) {
            return ResponseEntity.ok(optionalDireccionPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody DireccionPersona direccion_persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(direccion_personaService.save(direccion_persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<DireccionPersona> update(@PathVariable Long id, @Valid @RequestBody DireccionPersona direccion_persona) {
        Optional<DireccionPersona> direccion_personaOptional = this.direccion_personaService.update(id, direccion_persona);
        if (direccion_personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(direccion_personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<DireccionPersona> delete(@PathVariable Long id) {
        Optional<DireccionPersona> optionalDireccionPersona = this.direccion_personaService.delete(id);
        if (optionalDireccionPersona.isPresent()) {
            return ResponseEntity.ok(optionalDireccionPersona.orElseThrow());
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
