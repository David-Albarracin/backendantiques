
package pro.ddsr.backendantiques.modules.transacciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.transacciones.repository.TransaccionesRepository;
import pro.ddsr.backendantiques.modules.transacciones.entity.Transacciones;

@Service
public class TransaccionesService {
    @Autowired
    TransaccionesRepository transaccionesRepository;

    @Transactional
    public Optional<Transacciones> delete(Long id) {
        Optional<Transacciones> optionalTransacciones = this.transaccionesRepository.findById(id);
        optionalTransacciones.ifPresent(
            TransaccionesFound -> {
                this.transaccionesRepository.delete(TransaccionesFound);
            }
        );
        return optionalTransacciones;
    }
 
    public List<Transacciones> findAll() {
        return (List<Transacciones>) this.transaccionesRepository.findAll();
    }

    public Optional<Transacciones> findById(Long id) {
        return this.transaccionesRepository.findById(id);
    }

    public Transacciones save(Transacciones Transacciones) {
        return this.transaccionesRepository.save(Transacciones);
    }

    public Optional<Transacciones> update(Long id, Transacciones transacciones) {
        Optional<Transacciones> optionalTransacciones = this.transaccionesRepository.findById(id);
        if (optionalTransacciones.isPresent()) {
            Transacciones transaccionesItem = optionalTransacciones.orElseThrow();
            // Sets
            return Optional.of(this.transaccionesRepository.save(transaccionesItem));
        }
        return optionalTransacciones;
    }
}
