
package pro.ddsr.backendantiques.modules.despachos.controller;

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

import pro.ddsr.backendantiques.modules.despachos.service.DespachosService;
import pro.ddsr.backendantiques.modules.despachos.entity.Despachos;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/despachos")
public class DespachosController {

    @Autowired
    private DespachosService despachosService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Despachos> listDespachos() {
        return this.despachosService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Despachos> view(@PathVariable Long id) {
        Optional<Despachos> optionalDespachos = despachosService.findById(id);
        if (optionalDespachos.isPresent()) {
            return ResponseEntity.ok(optionalDespachos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Despachos despachos, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(despachosService.save(despachos));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Despachos> update(@PathVariable Long id, @Valid @RequestBody Despachos despachos) {
        Optional<Despachos> despachosOptional = this.despachosService.update(id, despachos);
        if (despachosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(despachosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Despachos> delete(@PathVariable Long id) {
        Optional<Despachos> optionalDespachos = this.despachosService.delete(id);
        if (optionalDespachos.isPresent()) {
            return ResponseEntity.ok(optionalDespachos.orElseThrow());
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
