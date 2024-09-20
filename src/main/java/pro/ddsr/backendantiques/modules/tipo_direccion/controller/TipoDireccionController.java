
package pro.ddsr.backendantiques.modules.tipo_direccion.controller;

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

import pro.ddsr.backendantiques.modules.tipo_direccion.service.TipoDireccionService;
import pro.ddsr.backendantiques.modules.tipo_direccion.entity.TipoDireccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo_direccion")
public class TipoDireccionController {

    @Autowired
    private TipoDireccionService tipo_direccionService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<TipoDireccion> listTipoDireccion() {
        return this.tipo_direccionService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<TipoDireccion> view(@PathVariable Long id) {
        Optional<TipoDireccion> optionalTipoDireccion = tipo_direccionService.findById(id);
        if (optionalTipoDireccion.isPresent()) {
            return ResponseEntity.ok(optionalTipoDireccion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody TipoDireccion tipo_direccion, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tipo_direccionService.save(tipo_direccion));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<TipoDireccion> update(@PathVariable Long id, @Valid @RequestBody TipoDireccion tipo_direccion) {
        Optional<TipoDireccion> tipo_direccionOptional = this.tipo_direccionService.update(id, tipo_direccion);
        if (tipo_direccionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipo_direccionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<TipoDireccion> delete(@PathVariable Long id) {
        Optional<TipoDireccion> optionalTipoDireccion = this.tipo_direccionService.delete(id);
        if (optionalTipoDireccion.isPresent()) {
            return ResponseEntity.ok(optionalTipoDireccion.orElseThrow());
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
