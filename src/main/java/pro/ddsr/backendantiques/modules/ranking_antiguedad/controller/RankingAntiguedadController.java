
package pro.ddsr.backendantiques.modules.ranking_antiguedad.controller;

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

import pro.ddsr.backendantiques.modules.ranking_antiguedad.service.RankingAntiguedadService;
import pro.ddsr.backendantiques.modules.ranking_antiguedad.entity.RankingAntiguedad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ranking_antiguedad")
public class RankingAntiguedadController {

    @Autowired
    private RankingAntiguedadService ranking_antiguedadService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<RankingAntiguedad> listRankingAntiguedad() {
        return this.ranking_antiguedadService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<RankingAntiguedad> view(@PathVariable Long id) {
        Optional<RankingAntiguedad> optionalRankingAntiguedad = ranking_antiguedadService.findById(id);
        if (optionalRankingAntiguedad.isPresent()) {
            return ResponseEntity.ok(optionalRankingAntiguedad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody RankingAntiguedad ranking_antiguedad, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ranking_antiguedadService.save(ranking_antiguedad));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<RankingAntiguedad> update(@PathVariable Long id, @Valid @RequestBody RankingAntiguedad ranking_antiguedad) {
        Optional<RankingAntiguedad> ranking_antiguedadOptional = this.ranking_antiguedadService.update(id, ranking_antiguedad);
        if (ranking_antiguedadOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ranking_antiguedadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<RankingAntiguedad> delete(@PathVariable Long id) {
        Optional<RankingAntiguedad> optionalRankingAntiguedad = this.ranking_antiguedadService.delete(id);
        if (optionalRankingAntiguedad.isPresent()) {
            return ResponseEntity.ok(optionalRankingAntiguedad.orElseThrow());
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
