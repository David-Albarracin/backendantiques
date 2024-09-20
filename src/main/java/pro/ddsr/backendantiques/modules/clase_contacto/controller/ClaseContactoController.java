
package pro.ddsr.backendantiques.modules.clase_contacto.controller;

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

import pro.ddsr.backendantiques.modules.clase_contacto.service.ClaseContactoService;
import pro.ddsr.backendantiques.modules.clase_contacto.entity.ClaseContacto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clase_contacto")
public class ClaseContactoController {

    @Autowired
    private ClaseContactoService clase_contactoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ClaseContacto> listClaseContacto() {
        return this.clase_contactoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ClaseContacto> view(@PathVariable Long id) {
        Optional<ClaseContacto> optionalClaseContacto = clase_contactoService.findById(id);
        if (optionalClaseContacto.isPresent()) {
            return ResponseEntity.ok(optionalClaseContacto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody ClaseContacto clase_contacto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clase_contactoService.save(clase_contacto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ClaseContacto> update(@PathVariable Long id, @Valid @RequestBody ClaseContacto clase_contacto) {
        Optional<ClaseContacto> clase_contactoOptional = this.clase_contactoService.update(id, clase_contacto);
        if (clase_contactoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clase_contactoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ClaseContacto> delete(@PathVariable Long id) {
        Optional<ClaseContacto> optionalClaseContacto = this.clase_contactoService.delete(id);
        if (optionalClaseContacto.isPresent()) {
            return ResponseEntity.ok(optionalClaseContacto.orElseThrow());
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
