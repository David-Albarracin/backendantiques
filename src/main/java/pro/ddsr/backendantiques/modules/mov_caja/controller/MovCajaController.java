
package pro.ddsr.backendantiques.modules.mov_caja.controller;

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

import pro.ddsr.backendantiques.modules.mov_caja.service.MovCajaService;
import pro.ddsr.backendantiques.modules.mov_caja.entity.MovCaja;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mov_caja")
public class MovCajaController {

    @Autowired
    private MovCajaService mov_cajaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<MovCaja> listMovCaja() {
        return this.mov_cajaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<MovCaja> view(@PathVariable Long id) {
        Optional<MovCaja> optionalMovCaja = mov_cajaService.findById(id);
        if (optionalMovCaja.isPresent()) {
            return ResponseEntity.ok(optionalMovCaja.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody MovCaja mov_caja, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mov_cajaService.save(mov_caja));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<MovCaja> update(@PathVariable Long id, @Valid @RequestBody MovCaja mov_caja) {
        Optional<MovCaja> mov_cajaOptional = this.mov_cajaService.update(id, mov_caja);
        if (mov_cajaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mov_cajaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<MovCaja> delete(@PathVariable Long id) {
        Optional<MovCaja> optionalMovCaja = this.mov_cajaService.delete(id);
        if (optionalMovCaja.isPresent()) {
            return ResponseEntity.ok(optionalMovCaja.orElseThrow());
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
