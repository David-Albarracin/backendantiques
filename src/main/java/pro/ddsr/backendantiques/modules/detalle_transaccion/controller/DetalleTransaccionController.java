
package pro.ddsr.backendantiques.modules.detalle_transaccion.controller;

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

import pro.ddsr.backendantiques.modules.detalle_transaccion.service.DetalleTransaccionService;
import pro.ddsr.backendantiques.modules.detalle_transaccion.entity.DetalleTransaccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/detalle_transaccion")
public class DetalleTransaccionController {

    @Autowired
    private DetalleTransaccionService detalle_transaccionService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<DetalleTransaccion> listDetalleTransaccion() {
        return this.detalle_transaccionService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DetalleTransaccion> view(@PathVariable Long id) {
        Optional<DetalleTransaccion> optionalDetalleTransaccion = detalle_transaccionService.findById(id);
        if (optionalDetalleTransaccion.isPresent()) {
            return ResponseEntity.ok(optionalDetalleTransaccion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody DetalleTransaccion detalle_transaccion, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle_transaccionService.save(detalle_transaccion));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<DetalleTransaccion> update(@PathVariable Long id, @Valid @RequestBody DetalleTransaccion detalle_transaccion) {
        Optional<DetalleTransaccion> detalle_transaccionOptional = this.detalle_transaccionService.update(id, detalle_transaccion);
        if (detalle_transaccionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(detalle_transaccionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<DetalleTransaccion> delete(@PathVariable Long id) {
        Optional<DetalleTransaccion> optionalDetalleTransaccion = this.detalle_transaccionService.delete(id);
        if (optionalDetalleTransaccion.isPresent()) {
            return ResponseEntity.ok(optionalDetalleTransaccion.orElseThrow());
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
