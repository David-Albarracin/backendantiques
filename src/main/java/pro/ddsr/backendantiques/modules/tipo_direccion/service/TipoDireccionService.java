
package pro.ddsr.backendantiques.modules.tipo_direccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.tipo_direccion.repository.TipoDireccionRepository;
import pro.ddsr.backendantiques.modules.tipo_direccion.entity.TipoDireccion;

@Service
public class TipoDireccionService {
    @Autowired
    TipoDireccionRepository tipo_direccionRepository;

    @Transactional
    public Optional<TipoDireccion> delete(Long id) {
        Optional<TipoDireccion> optionalTipoDireccion = this.tipo_direccionRepository.findById(id);
        optionalTipoDireccion.ifPresent(
            TipoDireccionFound -> {
                this.tipo_direccionRepository.delete(TipoDireccionFound);
            }
        );
        return optionalTipoDireccion;
    }
 
    public List<TipoDireccion> findAll() {
        return (List<TipoDireccion>) this.tipo_direccionRepository.findAll();
    }

    public Optional<TipoDireccion> findById(Long id) {
        return this.tipo_direccionRepository.findById(id);
    }

    public TipoDireccion save(TipoDireccion TipoDireccion) {
        return this.tipo_direccionRepository.save(TipoDireccion);
    }

    public Optional<TipoDireccion> update(Long id, TipoDireccion tipo_direccion) {
        Optional<TipoDireccion> optionalTipoDireccion = this.tipo_direccionRepository.findById(id);
        if (optionalTipoDireccion.isPresent()) {
            TipoDireccion tipo_direccionItem = optionalTipoDireccion.orElseThrow();
            // Sets
            return Optional.of(this.tipo_direccionRepository.save(tipo_direccionItem));
        }
        return optionalTipoDireccion;
    }
}
