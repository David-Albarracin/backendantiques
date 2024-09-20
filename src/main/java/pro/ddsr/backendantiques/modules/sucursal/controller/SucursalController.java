
package pro.ddsr.backendantiques.modules.sucursal.controller;

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

import pro.ddsr.backendantiques.modules.sucursal.service.SucursalService;
import pro.ddsr.backendantiques.modules.sucursal.entity.Sucursal;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Sucursal> listSucursal() {
        return this.sucursalService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Sucursal> view(@PathVariable Long id) {
        Optional<Sucursal> optionalSucursal = sucursalService.findById(id);
        if (optionalSucursal.isPresent()) {
            return ResponseEntity.ok(optionalSucursal.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Sucursal sucursal, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        Optional<Sucursal> sucursalOptional = this.sucursalService.update(id, sucursal);
        if (sucursalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Sucursal> delete(@PathVariable Long id) {
        Optional<Sucursal> optionalSucursal = this.sucursalService.delete(id);
        if (optionalSucursal.isPresent()) {
            return ResponseEntity.ok(optionalSucursal.orElseThrow());
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
