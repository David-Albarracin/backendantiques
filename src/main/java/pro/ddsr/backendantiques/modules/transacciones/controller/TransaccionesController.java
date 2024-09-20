
package pro.ddsr.backendantiques.modules.transacciones.controller;

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

import pro.ddsr.backendantiques.modules.transacciones.service.TransaccionesService;
import pro.ddsr.backendantiques.modules.transacciones.entity.Transacciones;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @Autowired
    private TransaccionesService transaccionesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Transacciones> listTransacciones() {
        return this.transaccionesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Transacciones> view(@PathVariable Long id) {
        Optional<Transacciones> optionalTransacciones = transaccionesService.findById(id);
        if (optionalTransacciones.isPresent()) {
            return ResponseEntity.ok(optionalTransacciones.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Transacciones transacciones, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionesService.save(transacciones));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Transacciones> update(@PathVariable Long id, @Valid @RequestBody Transacciones transacciones) {
        Optional<Transacciones> transaccionesOptional = this.transaccionesService.update(id, transacciones);
        if (transaccionesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transaccionesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Transacciones> delete(@PathVariable Long id) {
        Optional<Transacciones> optionalTransacciones = this.transaccionesService.delete(id);
        if (optionalTransacciones.isPresent()) {
            return ResponseEntity.ok(optionalTransacciones.orElseThrow());
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
