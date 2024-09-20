
package pro.ddsr.backendantiques.modules.galeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.galeria.repository.GaleriaRepository;
import pro.ddsr.backendantiques.modules.galeria.entity.Galeria;

@Service
public class GaleriaService {
    @Autowired
    GaleriaRepository galeriaRepository;

    @Transactional
    public Optional<Galeria> delete(Long id) {
        Optional<Galeria> optionalGaleria = this.galeriaRepository.findById(id);
        optionalGaleria.ifPresent(
            GaleriaFound -> {
                this.galeriaRepository.delete(GaleriaFound);
            }
        );
        return optionalGaleria;
    }
 
    public List<Galeria> findAll() {
        return (List<Galeria>) this.galeriaRepository.findAll();
    }

    public Optional<Galeria> findById(Long id) {
        return this.galeriaRepository.findById(id);
    }

    public Galeria save(Galeria Galeria) {
        return this.galeriaRepository.save(Galeria);
    }

    public Optional<Galeria> update(Long id, Galeria galeria) {
        Optional<Galeria> optionalGaleria = this.galeriaRepository.findById(id);
        if (optionalGaleria.isPresent()) {
            Galeria galeriaItem = optionalGaleria.orElseThrow();
            // Sets
            return Optional.of(this.galeriaRepository.save(galeriaItem));
        }
        return optionalGaleria;
    }
}
