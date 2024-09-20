
package pro.ddsr.backendantiques.modules.antiguedades.controller;

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

import pro.ddsr.backendantiques.modules.antiguedades.service.AntiguedadesService;
import pro.ddsr.backendantiques.modules.antiguedades.entity.Antiguedades;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/antiguedades")
public class AntiguedadesController {

    @Autowired
    private AntiguedadesService antiguedadesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Antiguedades> listAntiguedades() {
        return this.antiguedadesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Antiguedades> view(@PathVariable Long id) {
        Optional<Antiguedades> optionalAntiguedades = antiguedadesService.findById(id);
        if (optionalAntiguedades.isPresent()) {
            return ResponseEntity.ok(optionalAntiguedades.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Antiguedades antiguedades, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(antiguedadesService.save(antiguedades));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Antiguedades> update(@PathVariable Long id, @Valid @RequestBody Antiguedades antiguedades) {
        Optional<Antiguedades> antiguedadesOptional = this.antiguedadesService.update(id, antiguedades);
        if (antiguedadesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(antiguedadesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Antiguedades> delete(@PathVariable Long id) {
        Optional<Antiguedades> optionalAntiguedades = this.antiguedadesService.delete(id);
        if (optionalAntiguedades.isPresent()) {
            return ResponseEntity.ok(optionalAntiguedades.orElseThrow());
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
