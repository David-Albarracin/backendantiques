
package pro.ddsr.backendantiques.modules.despachos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.despachos.repository.DespachosRepository;
import pro.ddsr.backendantiques.modules.despachos.entity.Despachos;

@Service
public class DespachosService {
    @Autowired
    DespachosRepository despachosRepository;

    @Transactional
    public Optional<Despachos> delete(Long id) {
        Optional<Despachos> optionalDespachos = this.despachosRepository.findById(id);
        optionalDespachos.ifPresent(
            DespachosFound -> {
                this.despachosRepository.delete(DespachosFound);
            }
        );
        return optionalDespachos;
    }
 
    public List<Despachos> findAll() {
        return (List<Despachos>) this.despachosRepository.findAll();
    }

    public Optional<Despachos> findById(Long id) {
        return this.despachosRepository.findById(id);
    }

    public Despachos save(Despachos Despachos) {
        return this.despachosRepository.save(Despachos);
    }

    public Optional<Despachos> update(Long id, Despachos despachos) {
        Optional<Despachos> optionalDespachos = this.despachosRepository.findById(id);
        if (optionalDespachos.isPresent()) {
            Despachos despachosItem = optionalDespachos.orElseThrow();
            // Sets
            return Optional.of(this.despachosRepository.save(despachosItem));
        }
        return optionalDespachos;
    }
}
