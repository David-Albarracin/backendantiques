
package pro.ddsr.backendantiques.modules.tipom_caja.controller;

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

import pro.ddsr.backendantiques.modules.tipom_caja.service.TipomCajaService;
import pro.ddsr.backendantiques.modules.tipom_caja.entity.TipomCaja;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipom_caja")
public class TipomCajaController {

    @Autowired
    private TipomCajaService tipom_cajaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<TipomCaja> listTipomCaja() {
        return this.tipom_cajaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<TipomCaja> view(@PathVariable Long id) {
        Optional<TipomCaja> optionalTipomCaja = tipom_cajaService.findById(id);
        if (optionalTipomCaja.isPresent()) {
            return ResponseEntity.ok(optionalTipomCaja.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody TipomCaja tipom_caja, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tipom_cajaService.save(tipom_caja));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<TipomCaja> update(@PathVariable Long id, @Valid @RequestBody TipomCaja tipom_caja) {
        Optional<TipomCaja> tipom_cajaOptional = this.tipom_cajaService.update(id, tipom_caja);
        if (tipom_cajaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipom_cajaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<TipomCaja> delete(@PathVariable Long id) {
        Optional<TipomCaja> optionalTipomCaja = this.tipom_cajaService.delete(id);
        if (optionalTipomCaja.isPresent()) {
            return ResponseEntity.ok(optionalTipomCaja.orElseThrow());
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
