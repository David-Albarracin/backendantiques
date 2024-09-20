
package pro.ddsr.backendantiques.modules.epoca_antiguedad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.epoca_antiguedad.repository.EpocaAntiguedadRepository;
import pro.ddsr.backendantiques.modules.epoca_antiguedad.entity.EpocaAntiguedad;

@Service
public class EpocaAntiguedadService {
    @Autowired
    EpocaAntiguedadRepository epoca_antiguedadRepository;

    @Transactional
    public Optional<EpocaAntiguedad> delete(Long id) {
        Optional<EpocaAntiguedad> optionalEpocaAntiguedad = this.epoca_antiguedadRepository.findById(id);
        optionalEpocaAntiguedad.ifPresent(
            EpocaAntiguedadFound -> {
                this.epoca_antiguedadRepository.delete(EpocaAntiguedadFound);
            }
        );
        return optionalEpocaAntiguedad;
    }
 
    public List<EpocaAntiguedad> findAll() {
        return (List<EpocaAntiguedad>) this.epoca_antiguedadRepository.findAll();
    }

    public Optional<EpocaAntiguedad> findById(Long id) {
        return this.epoca_antiguedadRepository.findById(id);
    }

    public EpocaAntiguedad save(EpocaAntiguedad EpocaAntiguedad) {
        return this.epoca_antiguedadRepository.save(EpocaAntiguedad);
    }

    public Optional<EpocaAntiguedad> update(Long id, EpocaAntiguedad epoca_antiguedad) {
        Optional<EpocaAntiguedad> optionalEpocaAntiguedad = this.epoca_antiguedadRepository.findById(id);
        if (optionalEpocaAntiguedad.isPresent()) {
            EpocaAntiguedad epoca_antiguedadItem = optionalEpocaAntiguedad.orElseThrow();
            // Sets
            return Optional.of(this.epoca_antiguedadRepository.save(epoca_antiguedadItem));
        }
        return optionalEpocaAntiguedad;
    }
}
