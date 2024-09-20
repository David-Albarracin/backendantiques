
package pro.ddsr.backendantiques.modules.tipo_transaccion.controller;

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

import pro.ddsr.backendantiques.modules.tipo_transaccion.service.TipoTransaccionService;
import pro.ddsr.backendantiques.modules.tipo_transaccion.entity.TipoTransaccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo_transaccion")
public class TipoTransaccionController {

    @Autowired
    private TipoTransaccionService tipo_transaccionService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<TipoTransaccion> listTipoTransaccion() {
        return this.tipo_transaccionService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<TipoTransaccion> view(@PathVariable Long id) {
        Optional<TipoTransaccion> optionalTipoTransaccion = tipo_transaccionService.findById(id);
        if (optionalTipoTransaccion.isPresent()) {
            return ResponseEntity.ok(optionalTipoTransaccion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody TipoTransaccion tipo_transaccion, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tipo_transaccionService.save(tipo_transaccion));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<TipoTransaccion> update(@PathVariable Long id, @Valid @RequestBody TipoTransaccion tipo_transaccion) {
        Optional<TipoTransaccion> tipo_transaccionOptional = this.tipo_transaccionService.update(id, tipo_transaccion);
        if (tipo_transaccionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipo_transaccionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<TipoTransaccion> delete(@PathVariable Long id) {
        Optional<TipoTransaccion> optionalTipoTransaccion = this.tipo_transaccionService.delete(id);
        if (optionalTipoTransaccion.isPresent()) {
            return ResponseEntity.ok(optionalTipoTransaccion.orElseThrow());
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
