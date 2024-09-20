
package pro.ddsr.backendantiques.modules.estacion_pago.controller;

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

import pro.ddsr.backendantiques.modules.estacion_pago.service.EstacionPagoService;
import pro.ddsr.backendantiques.modules.estacion_pago.entity.EstacionPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estacion_pago")
public class EstacionPagoController {

    @Autowired
    private EstacionPagoService estacion_pagoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<EstacionPago> listEstacionPago() {
        return this.estacion_pagoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<EstacionPago> view(@PathVariable Long id) {
        Optional<EstacionPago> optionalEstacionPago = estacion_pagoService.findById(id);
        if (optionalEstacionPago.isPresent()) {
            return ResponseEntity.ok(optionalEstacionPago.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody EstacionPago estacion_pago, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estacion_pagoService.save(estacion_pago));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<EstacionPago> update(@PathVariable Long id, @Valid @RequestBody EstacionPago estacion_pago) {
        Optional<EstacionPago> estacion_pagoOptional = this.estacion_pagoService.update(id, estacion_pago);
        if (estacion_pagoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estacion_pagoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<EstacionPago> delete(@PathVariable Long id) {
        Optional<EstacionPago> optionalEstacionPago = this.estacion_pagoService.delete(id);
        if (optionalEstacionPago.isPresent()) {
            return ResponseEntity.ok(optionalEstacionPago.orElseThrow());
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
