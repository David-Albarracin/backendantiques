
package pro.ddsr.backendantiques.modules.medio_pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.medio_pago.repository.MedioPagoRepository;
import pro.ddsr.backendantiques.modules.medio_pago.entity.MedioPago;

@Service
public class MedioPagoService {
    @Autowired
    MedioPagoRepository medio_pagoRepository;

    @Transactional
    public Optional<MedioPago> delete(Long id) {
        Optional<MedioPago> optionalMedioPago = this.medio_pagoRepository.findById(id);
        optionalMedioPago.ifPresent(
            MedioPagoFound -> {
                this.medio_pagoRepository.delete(MedioPagoFound);
            }
        );
        return optionalMedioPago;
    }
 
    public List<MedioPago> findAll() {
        return (List<MedioPago>) this.medio_pagoRepository.findAll();
    }

    public Optional<MedioPago> findById(Long id) {
        return this.medio_pagoRepository.findById(id);
    }

    public MedioPago save(MedioPago MedioPago) {
        return this.medio_pagoRepository.save(MedioPago);
    }

    public Optional<MedioPago> update(Long id, MedioPago medio_pago) {
        Optional<MedioPago> optionalMedioPago = this.medio_pagoRepository.findById(id);
        if (optionalMedioPago.isPresent()) {
            MedioPago medio_pagoItem = optionalMedioPago.orElseThrow();
            // Sets
            return Optional.of(this.medio_pagoRepository.save(medio_pagoItem));
        }
        return optionalMedioPago;
    }
}
