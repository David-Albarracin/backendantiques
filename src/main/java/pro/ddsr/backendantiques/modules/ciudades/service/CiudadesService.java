
package pro.ddsr.backendantiques.modules.ciudades.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.ciudades.repository.CiudadesRepository;
import pro.ddsr.backendantiques.modules.ciudades.entity.Ciudades;

@Service
public class CiudadesService {
    @Autowired
    CiudadesRepository ciudadesRepository;

    @Transactional
    public Optional<Ciudades> delete(Long id) {
        Optional<Ciudades> optionalCiudades = this.ciudadesRepository.findById(id);
        optionalCiudades.ifPresent(
            CiudadesFound -> {
                this.ciudadesRepository.delete(CiudadesFound);
            }
        );
        return optionalCiudades;
    }
 
    public List<Ciudades> findAll() {
        return (List<Ciudades>) this.ciudadesRepository.findAll();
    }

    public Optional<Ciudades> findById(Long id) {
        return this.ciudadesRepository.findById(id);
    }

    public Ciudades save(Ciudades Ciudades) {
        return this.ciudadesRepository.save(Ciudades);
    }

    public Optional<Ciudades> update(Long id, Ciudades ciudades) {
        Optional<Ciudades> optionalCiudades = this.ciudadesRepository.findById(id);
        if (optionalCiudades.isPresent()) {
            Ciudades ciudadesItem = optionalCiudades.orElseThrow();
            // Sets
            return Optional.of(this.ciudadesRepository.save(ciudadesItem));
        }
        return optionalCiudades;
    }
}
