
package pro.ddsr.backendantiques.modules.detalle_transaccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.detalle_transaccion.repository.DetalleTransaccionRepository;
import pro.ddsr.backendantiques.modules.detalle_transaccion.entity.DetalleTransaccion;

@Service
public class DetalleTransaccionService {
    @Autowired
    DetalleTransaccionRepository detalle_transaccionRepository;

    @Transactional
    public Optional<DetalleTransaccion> delete(Long id) {
        Optional<DetalleTransaccion> optionalDetalleTransaccion = this.detalle_transaccionRepository.findById(id);
        optionalDetalleTransaccion.ifPresent(
            DetalleTransaccionFound -> {
                this.detalle_transaccionRepository.delete(DetalleTransaccionFound);
            }
        );
        return optionalDetalleTransaccion;
    }
 
    public List<DetalleTransaccion> findAll() {
        return (List<DetalleTransaccion>) this.detalle_transaccionRepository.findAll();
    }

    public Optional<DetalleTransaccion> findById(Long id) {
        return this.detalle_transaccionRepository.findById(id);
    }

    public DetalleTransaccion save(DetalleTransaccion DetalleTransaccion) {
        return this.detalle_transaccionRepository.save(DetalleTransaccion);
    }

    public Optional<DetalleTransaccion> update(Long id, DetalleTransaccion detalle_transaccion) {
        Optional<DetalleTransaccion> optionalDetalleTransaccion = this.detalle_transaccionRepository.findById(id);
        if (optionalDetalleTransaccion.isPresent()) {
            DetalleTransaccion detalle_transaccionItem = optionalDetalleTransaccion.orElseThrow();
            // Sets
            return Optional.of(this.detalle_transaccionRepository.save(detalle_transaccionItem));
        }
        return optionalDetalleTransaccion;
    }
}
