
package pro.ddsr.backendantiques.modules.coleccionistas.controller;

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

import pro.ddsr.backendantiques.modules.coleccionistas.service.ColeccionistasService;
import pro.ddsr.backendantiques.modules.coleccionistas.entity.Coleccionistas;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/coleccionistas")
public class ColeccionistasController {

    @Autowired
    private ColeccionistasService coleccionistasService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Coleccionistas> listColeccionistas() {
        return this.coleccionistasService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Coleccionistas> view(@PathVariable Long id) {
        Optional<Coleccionistas> optionalColeccionistas = coleccionistasService.findById(id);
        if (optionalColeccionistas.isPresent()) {
            return ResponseEntity.ok(optionalColeccionistas.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Coleccionistas coleccionistas, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(coleccionistasService.save(coleccionistas));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Coleccionistas> update(@PathVariable Long id, @Valid @RequestBody Coleccionistas coleccionistas) {
        Optional<Coleccionistas> coleccionistasOptional = this.coleccionistasService.update(id, coleccionistas);
        if (coleccionistasOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(coleccionistasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Coleccionistas> delete(@PathVariable Long id) {
        Optional<Coleccionistas> optionalColeccionistas = this.coleccionistasService.delete(id);
        if (optionalColeccionistas.isPresent()) {
            return ResponseEntity.ok(optionalColeccionistas.orElseThrow());
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
