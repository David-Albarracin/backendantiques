
package pro.ddsr.backendantiques.modules.regiones.controller;

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

import pro.ddsr.backendantiques.modules.regiones.service.RegionesService;
import pro.ddsr.backendantiques.modules.regiones.entity.Regiones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regiones")
public class RegionesController {

    @Autowired
    private RegionesService regionesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Regiones> listRegiones() {
        return this.regionesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Regiones> view(@PathVariable Long id) {
        Optional<Regiones> optionalRegiones = regionesService.findById(id);
        if (optionalRegiones.isPresent()) {
            return ResponseEntity.ok(optionalRegiones.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Regiones regiones, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionesService.save(regiones));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Regiones> update(@PathVariable Long id, @Valid @RequestBody Regiones regiones) {
        Optional<Regiones> regionesOptional = this.regionesService.update(id, regiones);
        if (regionesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(regionesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Regiones> delete(@PathVariable Long id) {
        Optional<Regiones> optionalRegiones = this.regionesService.delete(id);
        if (optionalRegiones.isPresent()) {
            return ResponseEntity.ok(optionalRegiones.orElseThrow());
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
