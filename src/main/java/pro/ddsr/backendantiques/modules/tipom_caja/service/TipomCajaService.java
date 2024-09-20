
package pro.ddsr.backendantiques.modules.tipom_caja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.tipom_caja.repository.TipomCajaRepository;
import pro.ddsr.backendantiques.modules.tipom_caja.entity.TipomCaja;

@Service
public class TipomCajaService {
    @Autowired
    TipomCajaRepository tipom_cajaRepository;

    @Transactional
    public Optional<TipomCaja> delete(Long id) {
        Optional<TipomCaja> optionalTipomCaja = this.tipom_cajaRepository.findById(id);
        optionalTipomCaja.ifPresent(
            TipomCajaFound -> {
                this.tipom_cajaRepository.delete(TipomCajaFound);
            }
        );
        return optionalTipomCaja;
    }
 
    public List<TipomCaja> findAll() {
        return (List<TipomCaja>) this.tipom_cajaRepository.findAll();
    }

    public Optional<TipomCaja> findById(Long id) {
        return this.tipom_cajaRepository.findById(id);
    }

    public TipomCaja save(TipomCaja TipomCaja) {
        return this.tipom_cajaRepository.save(TipomCaja);
    }

    public Optional<TipomCaja> update(Long id, TipomCaja tipom_caja) {
        Optional<TipomCaja> optionalTipomCaja = this.tipom_cajaRepository.findById(id);
        if (optionalTipomCaja.isPresent()) {
            TipomCaja tipom_cajaItem = optionalTipomCaja.orElseThrow();
            // Sets
            return Optional.of(this.tipom_cajaRepository.save(tipom_cajaItem));
        }
        return optionalTipomCaja;
    }
}
