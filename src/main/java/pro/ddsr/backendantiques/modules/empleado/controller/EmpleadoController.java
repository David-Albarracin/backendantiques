
package pro.ddsr.backendantiques.modules.empleado.controller;

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

import pro.ddsr.backendantiques.modules.empleado.service.EmpleadoService;
import pro.ddsr.backendantiques.modules.empleado.entity.Empleado;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Empleado> listEmpleado() {
        return this.empleadoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Empleado> view(@PathVariable Long id) {
        Optional<Empleado> optionalEmpleado = empleadoService.findById(id);
        if (optionalEmpleado.isPresent()) {
            return ResponseEntity.ok(optionalEmpleado.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.save(empleado));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Empleado> update(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        Optional<Empleado> empleadoOptional = this.empleadoService.update(id, empleado);
        if (empleadoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Empleado> delete(@PathVariable Long id) {
        Optional<Empleado> optionalEmpleado = this.empleadoService.delete(id);
        if (optionalEmpleado.isPresent()) {
            return ResponseEntity.ok(optionalEmpleado.orElseThrow());
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
