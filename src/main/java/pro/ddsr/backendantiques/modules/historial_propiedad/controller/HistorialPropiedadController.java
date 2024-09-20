
package pro.ddsr.backendantiques.modules.historial_propiedad.controller;

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

import pro.ddsr.backendantiques.modules.historial_propiedad.service.HistorialPropiedadService;
import pro.ddsr.backendantiques.modules.historial_propiedad.entity.HistorialPropiedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/historial_propiedad")
public class HistorialPropiedadController {

    @Autowired
    private HistorialPropiedadService historial_propiedadService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<HistorialPropiedad> listHistorialPropiedad() {
        return this.historial_propiedadService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<HistorialPropiedad> view(@PathVariable Long id) {
        Optional<HistorialPropiedad> optionalHistorialPropiedad = historial_propiedadService.findById(id);
        if (optionalHistorialPropiedad.isPresent()) {
            return ResponseEntity.ok(optionalHistorialPropiedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody HistorialPropiedad historial_propiedad, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(historial_propiedadService.save(historial_propiedad));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<HistorialPropiedad> update(@PathVariable Long id, @Valid @RequestBody HistorialPropiedad historial_propiedad) {
        Optional<HistorialPropiedad> historial_propiedadOptional = this.historial_propiedadService.update(id, historial_propiedad);
        if (historial_propiedadOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(historial_propiedadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<HistorialPropiedad> delete(@PathVariable Long id) {
        Optional<HistorialPropiedad> optionalHistorialPropiedad = this.historial_propiedadService.delete(id);
        if (optionalHistorialPropiedad.isPresent()) {
            return ResponseEntity.ok(optionalHistorialPropiedad.orElseThrow());
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
