
package pro.ddsr.backendantiques.modules.epoca_antiguedad.controller;

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

import pro.ddsr.backendantiques.modules.epoca_antiguedad.service.EpocaAntiguedadService;
import pro.ddsr.backendantiques.modules.epoca_antiguedad.entity.EpocaAntiguedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/epoca_antiguedad")
public class EpocaAntiguedadController {

    @Autowired
    private EpocaAntiguedadService epoca_antiguedadService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<EpocaAntiguedad> listEpocaAntiguedad() {
        return this.epoca_antiguedadService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<EpocaAntiguedad> view(@PathVariable Long id) {
        Optional<EpocaAntiguedad> optionalEpocaAntiguedad = epoca_antiguedadService.findById(id);
        if (optionalEpocaAntiguedad.isPresent()) {
            return ResponseEntity.ok(optionalEpocaAntiguedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody EpocaAntiguedad epoca_antiguedad, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(epoca_antiguedadService.save(epoca_antiguedad));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<EpocaAntiguedad> update(@PathVariable Long id, @Valid @RequestBody EpocaAntiguedad epoca_antiguedad) {
        Optional<EpocaAntiguedad> epoca_antiguedadOptional = this.epoca_antiguedadService.update(id, epoca_antiguedad);
        if (epoca_antiguedadOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(epoca_antiguedadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<EpocaAntiguedad> delete(@PathVariable Long id) {
        Optional<EpocaAntiguedad> optionalEpocaAntiguedad = this.epoca_antiguedadService.delete(id);
        if (optionalEpocaAntiguedad.isPresent()) {
            return ResponseEntity.ok(optionalEpocaAntiguedad.orElseThrow());
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
