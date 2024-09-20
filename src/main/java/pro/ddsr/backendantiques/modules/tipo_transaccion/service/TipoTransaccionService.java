
package pro.ddsr.backendantiques.modules.tipo_transaccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.tipo_transaccion.repository.TipoTransaccionRepository;
import pro.ddsr.backendantiques.modules.tipo_transaccion.entity.TipoTransaccion;

@Service
public class TipoTransaccionService {
    @Autowired
    TipoTransaccionRepository tipo_transaccionRepository;

    @Transactional
    public Optional<TipoTransaccion> delete(Long id) {
        Optional<TipoTransaccion> optionalTipoTransaccion = this.tipo_transaccionRepository.findById(id);
        optionalTipoTransaccion.ifPresent(
            TipoTransaccionFound -> {
                this.tipo_transaccionRepository.delete(TipoTransaccionFound);
            }
        );
        return optionalTipoTransaccion;
    }
 
    public List<TipoTransaccion> findAll() {
        return (List<TipoTransaccion>) this.tipo_transaccionRepository.findAll();
    }

    public Optional<TipoTransaccion> findById(Long id) {
        return this.tipo_transaccionRepository.findById(id);
    }

    public TipoTransaccion save(TipoTransaccion TipoTransaccion) {
        return this.tipo_transaccionRepository.save(TipoTransaccion);
    }

    public Optional<TipoTransaccion> update(Long id, TipoTransaccion tipo_transaccion) {
        Optional<TipoTransaccion> optionalTipoTransaccion = this.tipo_transaccionRepository.findById(id);
        if (optionalTipoTransaccion.isPresent()) {
            TipoTransaccion tipo_transaccionItem = optionalTipoTransaccion.orElseThrow();
            // Sets
            return Optional.of(this.tipo_transaccionRepository.save(tipo_transaccionItem));
        }
        return optionalTipoTransaccion;
    }
}
