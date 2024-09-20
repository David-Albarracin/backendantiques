
package pro.ddsr.backendantiques.modules.historial_propiedad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.historial_propiedad.repository.HistorialPropiedadRepository;
import pro.ddsr.backendantiques.modules.historial_propiedad.entity.HistorialPropiedad;

@Service
public class HistorialPropiedadService {
    @Autowired
    HistorialPropiedadRepository historial_propiedadRepository;

    @Transactional
    public Optional<HistorialPropiedad> delete(Long id) {
        Optional<HistorialPropiedad> optionalHistorialPropiedad = this.historial_propiedadRepository.findById(id);
        optionalHistorialPropiedad.ifPresent(
            HistorialPropiedadFound -> {
                this.historial_propiedadRepository.delete(HistorialPropiedadFound);
            }
        );
        return optionalHistorialPropiedad;
    }
 
    public List<HistorialPropiedad> findAll() {
        return (List<HistorialPropiedad>) this.historial_propiedadRepository.findAll();
    }

    public Optional<HistorialPropiedad> findById(Long id) {
        return this.historial_propiedadRepository.findById(id);
    }

    public HistorialPropiedad save(HistorialPropiedad HistorialPropiedad) {
        return this.historial_propiedadRepository.save(HistorialPropiedad);
    }

    public Optional<HistorialPropiedad> update(Long id, HistorialPropiedad historial_propiedad) {
        Optional<HistorialPropiedad> optionalHistorialPropiedad = this.historial_propiedadRepository.findById(id);
        if (optionalHistorialPropiedad.isPresent()) {
            HistorialPropiedad historial_propiedadItem = optionalHistorialPropiedad.orElseThrow();
            // Sets
            return Optional.of(this.historial_propiedadRepository.save(historial_propiedadItem));
        }
        return optionalHistorialPropiedad;
    }
}
