
package pro.ddsr.backendantiques.modules.ciudades.controller;

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

import pro.ddsr.backendantiques.modules.ciudades.service.CiudadesService;
import pro.ddsr.backendantiques.modules.ciudades.entity.Ciudades;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ciudades")
public class CiudadesController {

    @Autowired
    private CiudadesService ciudadesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Ciudades> listCiudades() {
        return this.ciudadesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Ciudades> view(@PathVariable Long id) {
        Optional<Ciudades> optionalCiudades = ciudadesService.findById(id);
        if (optionalCiudades.isPresent()) {
            return ResponseEntity.ok(optionalCiudades.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Ciudades ciudades, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadesService.save(ciudades));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Ciudades> update(@PathVariable Long id, @Valid @RequestBody Ciudades ciudades) {
        Optional<Ciudades> ciudadesOptional = this.ciudadesService.update(id, ciudades);
        if (ciudadesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ciudadesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Ciudades> delete(@PathVariable Long id) {
        Optional<Ciudades> optionalCiudades = this.ciudadesService.delete(id);
        if (optionalCiudades.isPresent()) {
            return ResponseEntity.ok(optionalCiudades.orElseThrow());
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
