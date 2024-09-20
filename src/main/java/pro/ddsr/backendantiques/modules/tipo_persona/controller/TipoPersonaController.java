
package pro.ddsr.backendantiques.modules.tipo_persona.controller;

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

import pro.ddsr.backendantiques.modules.tipo_persona.service.TipoPersonaService;
import pro.ddsr.backendantiques.modules.tipo_persona.entity.TipoPersona;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo_persona")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService tipo_personaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<TipoPersona> listTipoPersona() {
        return this.tipo_personaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<TipoPersona> view(@PathVariable Long id) {
        Optional<TipoPersona> optionalTipoPersona = tipo_personaService.findById(id);
        if (optionalTipoPersona.isPresent()) {
            return ResponseEntity.ok(optionalTipoPersona.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody TipoPersona tipo_persona, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tipo_personaService.save(tipo_persona));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<TipoPersona> update(@PathVariable Long id, @Valid @RequestBody TipoPersona tipo_persona) {
        Optional<TipoPersona> tipo_personaOptional = this.tipo_personaService.update(id, tipo_persona);
        if (tipo_personaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipo_personaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<TipoPersona> delete(@PathVariable Long id) {
        Optional<TipoPersona> optionalTipoPersona = this.tipo_personaService.delete(id);
        if (optionalTipoPersona.isPresent()) {
            return ResponseEntity.ok(optionalTipoPersona.orElseThrow());
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
