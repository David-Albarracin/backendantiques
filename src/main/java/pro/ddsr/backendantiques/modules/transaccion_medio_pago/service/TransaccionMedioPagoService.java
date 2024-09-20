
package pro.ddsr.backendantiques.modules.transaccion_medio_pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.transaccion_medio_pago.repository.TransaccionMedioPagoRepository;
import pro.ddsr.backendantiques.modules.transaccion_medio_pago.entity.TransaccionMedioPago;

@Service
public class TransaccionMedioPagoService {
    @Autowired
    TransaccionMedioPagoRepository transaccion_medio_pagoRepository;

    @Transactional
    public Optional<TransaccionMedioPago> delete(Long id) {
        Optional<TransaccionMedioPago> optionalTransaccionMedioPago = this.transaccion_medio_pagoRepository.findById(id);
        optionalTransaccionMedioPago.ifPresent(
            TransaccionMedioPagoFound -> {
                this.transaccion_medio_pagoRepository.delete(TransaccionMedioPagoFound);
            }
        );
        return optionalTransaccionMedioPago;
    }
 
    public List<TransaccionMedioPago> findAll() {
        return (List<TransaccionMedioPago>) this.transaccion_medio_pagoRepository.findAll();
    }

    public Optional<TransaccionMedioPago> findById(Long id) {
        return this.transaccion_medio_pagoRepository.findById(id);
    }

    public TransaccionMedioPago save(TransaccionMedioPago TransaccionMedioPago) {
        return this.transaccion_medio_pagoRepository.save(TransaccionMedioPago);
    }

    public Optional<TransaccionMedioPago> update(Long id, TransaccionMedioPago transaccion_medio_pago) {
        Optional<TransaccionMedioPago> optionalTransaccionMedioPago = this.transaccion_medio_pagoRepository.findById(id);
        if (optionalTransaccionMedioPago.isPresent()) {
            TransaccionMedioPago transaccion_medio_pagoItem = optionalTransaccionMedioPago.orElseThrow();
            // Sets
            return Optional.of(this.transaccion_medio_pagoRepository.save(transaccion_medio_pagoItem));
        }
        return optionalTransaccionMedioPago;
    }
}
