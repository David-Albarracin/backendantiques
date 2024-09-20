
package pro.ddsr.backendantiques.modules.medio_pago.controller;

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

import pro.ddsr.backendantiques.modules.medio_pago.service.MedioPagoService;
import pro.ddsr.backendantiques.modules.medio_pago.entity.MedioPago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medio_pago")
public class MedioPagoController {

    @Autowired
    private MedioPagoService medio_pagoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<MedioPago> listMedioPago() {
        return this.medio_pagoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<MedioPago> view(@PathVariable Long id) {
        Optional<MedioPago> optionalMedioPago = medio_pagoService.findById(id);
        if (optionalMedioPago.isPresent()) {
            return ResponseEntity.ok(optionalMedioPago.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody MedioPago medio_pago, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(medio_pagoService.save(medio_pago));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<MedioPago> update(@PathVariable Long id, @Valid @RequestBody MedioPago medio_pago) {
        Optional<MedioPago> medio_pagoOptional = this.medio_pagoService.update(id, medio_pago);
        if (medio_pagoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medio_pagoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<MedioPago> delete(@PathVariable Long id) {
        Optional<MedioPago> optionalMedioPago = this.medio_pagoService.delete(id);
        if (optionalMedioPago.isPresent()) {
            return ResponseEntity.ok(optionalMedioPago.orElseThrow());
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
