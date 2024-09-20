
package pro.ddsr.backendantiques.modules.transaccion_medio_pago.controller;

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

import pro.ddsr.backendantiques.modules.transaccion_medio_pago.service.TransaccionMedioPagoService;
import pro.ddsr.backendantiques.modules.transaccion_medio_pago.entity.TransaccionMedioPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaccion_medio_pago")
public class TransaccionMedioPagoController {

    @Autowired
    private TransaccionMedioPagoService transaccion_medio_pagoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<TransaccionMedioPago> listTransaccionMedioPago() {
        return this.transaccion_medio_pagoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<TransaccionMedioPago> view(@PathVariable Long id) {
        Optional<TransaccionMedioPago> optionalTransaccionMedioPago = transaccion_medio_pagoService.findById(id);
        if (optionalTransaccionMedioPago.isPresent()) {
            return ResponseEntity.ok(optionalTransaccionMedioPago.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody TransaccionMedioPago transaccion_medio_pago, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccion_medio_pagoService.save(transaccion_medio_pago));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<TransaccionMedioPago> update(@PathVariable Long id, @Valid @RequestBody TransaccionMedioPago transaccion_medio_pago) {
        Optional<TransaccionMedioPago> transaccion_medio_pagoOptional = this.transaccion_medio_pagoService.update(id, transaccion_medio_pago);
        if (transaccion_medio_pagoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transaccion_medio_pagoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<TransaccionMedioPago> delete(@PathVariable Long id) {
        Optional<TransaccionMedioPago> optionalTransaccionMedioPago = this.transaccion_medio_pagoService.delete(id);
        if (optionalTransaccionMedioPago.isPresent()) {
            return ResponseEntity.ok(optionalTransaccionMedioPago.orElseThrow());
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
