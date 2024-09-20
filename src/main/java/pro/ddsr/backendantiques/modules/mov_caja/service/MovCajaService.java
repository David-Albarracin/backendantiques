
package pro.ddsr.backendantiques.modules.mov_caja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.mov_caja.repository.MovCajaRepository;
import pro.ddsr.backendantiques.modules.mov_caja.entity.MovCaja;

@Service
public class MovCajaService {
    @Autowired
    MovCajaRepository mov_cajaRepository;

    @Transactional
    public Optional<MovCaja> delete(Long id) {
        Optional<MovCaja> optionalMovCaja = this.mov_cajaRepository.findById(id);
        optionalMovCaja.ifPresent(
            MovCajaFound -> {
                this.mov_cajaRepository.delete(MovCajaFound);
            }
        );
        return optionalMovCaja;
    }
 
    public List<MovCaja> findAll() {
        return (List<MovCaja>) this.mov_cajaRepository.findAll();
    }

    public Optional<MovCaja> findById(Long id) {
        return this.mov_cajaRepository.findById(id);
    }

    public MovCaja save(MovCaja MovCaja) {
        return this.mov_cajaRepository.save(MovCaja);
    }

    public Optional<MovCaja> update(Long id, MovCaja mov_caja) {
        Optional<MovCaja> optionalMovCaja = this.mov_cajaRepository.findById(id);
        if (optionalMovCaja.isPresent()) {
            MovCaja mov_cajaItem = optionalMovCaja.orElseThrow();
            // Sets
            return Optional.of(this.mov_cajaRepository.save(mov_cajaItem));
        }
        return optionalMovCaja;
    }
}
