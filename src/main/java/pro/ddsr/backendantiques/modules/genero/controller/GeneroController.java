
package pro.ddsr.backendantiques.modules.genero.controller;

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

import pro.ddsr.backendantiques.modules.genero.service.GeneroService;
import pro.ddsr.backendantiques.modules.genero.entity.Genero;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Genero> listGenero() {
        return this.generoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Genero> view(@PathVariable Long id) {
        Optional<Genero> optionalGenero = generoService.findById(id);
        if (optionalGenero.isPresent()) {
            return ResponseEntity.ok(optionalGenero.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Genero genero, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Genero> update(@PathVariable Long id, @Valid @RequestBody Genero genero) {
        Optional<Genero> generoOptional = this.generoService.update(id, genero);
        if (generoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Genero> delete(@PathVariable Long id) {
        Optional<Genero> optionalGenero = this.generoService.delete(id);
        if (optionalGenero.isPresent()) {
            return ResponseEntity.ok(optionalGenero.orElseThrow());
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
