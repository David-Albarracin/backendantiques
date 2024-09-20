
package pro.ddsr.backendantiques.modules.galeria.controller;

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

import pro.ddsr.backendantiques.modules.galeria.service.GaleriaService;
import pro.ddsr.backendantiques.modules.galeria.entity.Galeria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/galeria")
public class GaleriaController {

    @Autowired
    private GaleriaService galeriaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Galeria> listGaleria() {
        return this.galeriaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Galeria> view(@PathVariable Long id) {
        Optional<Galeria> optionalGaleria = galeriaService.findById(id);
        if (optionalGaleria.isPresent()) {
            return ResponseEntity.ok(optionalGaleria.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Galeria galeria, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(galeriaService.save(galeria));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Galeria> update(@PathVariable Long id, @Valid @RequestBody Galeria galeria) {
        Optional<Galeria> galeriaOptional = this.galeriaService.update(id, galeria);
        if (galeriaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(galeriaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Galeria> delete(@PathVariable Long id) {
        Optional<Galeria> optionalGaleria = this.galeriaService.delete(id);
        if (optionalGaleria.isPresent()) {
            return ResponseEntity.ok(optionalGaleria.orElseThrow());
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
