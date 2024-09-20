
package pro.ddsr.backendantiques.modules.estacion_pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.estacion_pago.repository.EstacionPagoRepository;
import pro.ddsr.backendantiques.modules.estacion_pago.entity.EstacionPago;

@Service
public class EstacionPagoService {
    @Autowired
    EstacionPagoRepository estacion_pagoRepository;

    @Transactional
    public Optional<EstacionPago> delete(Long id) {
        Optional<EstacionPago> optionalEstacionPago = this.estacion_pagoRepository.findById(id);
        optionalEstacionPago.ifPresent(
            EstacionPagoFound -> {
                this.estacion_pagoRepository.delete(EstacionPagoFound);
            }
        );
        return optionalEstacionPago;
    }
 
    public List<EstacionPago> findAll() {
        return (List<EstacionPago>) this.estacion_pagoRepository.findAll();
    }

    public Optional<EstacionPago> findById(Long id) {
        return this.estacion_pagoRepository.findById(id);
    }

    public EstacionPago save(EstacionPago EstacionPago) {
        return this.estacion_pagoRepository.save(EstacionPago);
    }

    public Optional<EstacionPago> update(Long id, EstacionPago estacion_pago) {
        Optional<EstacionPago> optionalEstacionPago = this.estacion_pagoRepository.findById(id);
        if (optionalEstacionPago.isPresent()) {
            EstacionPago estacion_pagoItem = optionalEstacionPago.orElseThrow();
            // Sets
            return Optional.of(this.estacion_pagoRepository.save(estacion_pagoItem));
        }
        return optionalEstacionPago;
    }
}
