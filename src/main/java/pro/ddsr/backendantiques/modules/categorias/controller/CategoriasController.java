
package pro.ddsr.backendantiques.modules.categorias.controller;

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

import pro.ddsr.backendantiques.modules.categorias.service.CategoriasService;
import pro.ddsr.backendantiques.modules.categorias.entity.Categorias;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Categorias> listCategorias() {
        return this.categoriasService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Categorias> view(@PathVariable Long id) {
        Optional<Categorias> optionalCategorias = categoriasService.findById(id);
        if (optionalCategorias.isPresent()) {
            return ResponseEntity.ok(optionalCategorias.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Categorias categorias, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasService.save(categorias));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Categorias> update(@PathVariable Long id, @Valid @RequestBody Categorias categorias) {
        Optional<Categorias> categoriasOptional = this.categoriasService.update(id, categorias);
        if (categoriasOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Categorias> delete(@PathVariable Long id) {
        Optional<Categorias> optionalCategorias = this.categoriasService.delete(id);
        if (optionalCategorias.isPresent()) {
            return ResponseEntity.ok(optionalCategorias.orElseThrow());
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
