
package pro.ddsr.backendantiques.modules.coleccionistas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.coleccionistas.repository.ColeccionistasRepository;
import pro.ddsr.backendantiques.modules.coleccionistas.entity.Coleccionistas;

@Service
public class ColeccionistasService {
    @Autowired
    ColeccionistasRepository coleccionistasRepository;

    @Transactional
    public Optional<Coleccionistas> delete(Long id) {
        Optional<Coleccionistas> optionalColeccionistas = this.coleccionistasRepository.findById(id);
        optionalColeccionistas.ifPresent(
            ColeccionistasFound -> {
                this.coleccionistasRepository.delete(ColeccionistasFound);
            }
        );
        return optionalColeccionistas;
    }
 
    public List<Coleccionistas> findAll() {
        return (List<Coleccionistas>) this.coleccionistasRepository.findAll();
    }

    public Optional<Coleccionistas> findById(Long id) {
        return this.coleccionistasRepository.findById(id);
    }

    public Coleccionistas save(Coleccionistas Coleccionistas) {
        return this.coleccionistasRepository.save(Coleccionistas);
    }

    public Optional<Coleccionistas> update(Long id, Coleccionistas coleccionistas) {
        Optional<Coleccionistas> optionalColeccionistas = this.coleccionistasRepository.findById(id);
        if (optionalColeccionistas.isPresent()) {
            Coleccionistas coleccionistasItem = optionalColeccionistas.orElseThrow();
            // Sets
            return Optional.of(this.coleccionistasRepository.save(coleccionistasItem));
        }
        return optionalColeccionistas;
    }
}
