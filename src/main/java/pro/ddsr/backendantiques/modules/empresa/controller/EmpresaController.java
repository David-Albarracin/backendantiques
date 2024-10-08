
package pro.ddsr.backendantiques.modules.empresa.controller;

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

import pro.ddsr.backendantiques.modules.empresa.service.EmpresaService;
import pro.ddsr.backendantiques.modules.empresa.entity.Empresa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Empresa> listEmpresa() {
        return this.empresaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Empresa> view(@PathVariable Long id) {
        Optional<Empresa> optionalEmpresa = empresaService.findById(id);
        if (optionalEmpresa.isPresent()) {
            return ResponseEntity.ok(optionalEmpresa.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Empresa empresa, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresa));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
        Optional<Empresa> empresaOptional = this.empresaService.update(id, empresa);
        if (empresaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Empresa> delete(@PathVariable Long id) {
        Optional<Empresa> optionalEmpresa = this.empresaService.delete(id);
        if (optionalEmpresa.isPresent()) {
            return ResponseEntity.ok(optionalEmpresa.orElseThrow());
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
