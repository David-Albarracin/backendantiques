
package pro.ddsr.backendantiques.modules.paises.controller;

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

import pro.ddsr.backendantiques.modules.paises.service.PaisesService;
import pro.ddsr.backendantiques.modules.paises.entity.Paises;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    @Autowired
    private PaisesService paisesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Paises> listPaises() {
        return this.paisesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Paises> view(@PathVariable Long id) {
        Optional<Paises> optionalPaises = paisesService.findById(id);
        if (optionalPaises.isPresent()) {
            return ResponseEntity.ok(optionalPaises.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Paises paises, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(paisesService.save(paises));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Paises> update(@PathVariable Long id, @Valid @RequestBody Paises paises) {
        Optional<Paises> paisesOptional = this.paisesService.update(id, paises);
        if (paisesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(paisesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Paises> delete(@PathVariable Long id) {
        Optional<Paises> optionalPaises = this.paisesService.delete(id);
        if (optionalPaises.isPresent()) {
            return ResponseEntity.ok(optionalPaises.orElseThrow());
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
