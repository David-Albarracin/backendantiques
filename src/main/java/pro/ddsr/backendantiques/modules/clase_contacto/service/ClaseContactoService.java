
package pro.ddsr.backendantiques.modules.clase_contacto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.clase_contacto.repository.ClaseContactoRepository;
import pro.ddsr.backendantiques.modules.clase_contacto.entity.ClaseContacto;

@Service
public class ClaseContactoService {
    @Autowired
    ClaseContactoRepository clase_contactoRepository;

    @Transactional
    public Optional<ClaseContacto> delete(Long id) {
        Optional<ClaseContacto> optionalClaseContacto = this.clase_contactoRepository.findById(id);
        optionalClaseContacto.ifPresent(
            ClaseContactoFound -> {
                this.clase_contactoRepository.delete(ClaseContactoFound);
            }
        );
        return optionalClaseContacto;
    }
 
    public List<ClaseContacto> findAll() {
        return (List<ClaseContacto>) this.clase_contactoRepository.findAll();
    }

    public Optional<ClaseContacto> findById(Long id) {
        return this.clase_contactoRepository.findById(id);
    }

    public ClaseContacto save(ClaseContacto ClaseContacto) {
        return this.clase_contactoRepository.save(ClaseContacto);
    }

    public Optional<ClaseContacto> update(Long id, ClaseContacto clase_contacto) {
        Optional<ClaseContacto> optionalClaseContacto = this.clase_contactoRepository.findById(id);
        if (optionalClaseContacto.isPresent()) {
            ClaseContacto clase_contactoItem = optionalClaseContacto.orElseThrow();
            // Sets
            return Optional.of(this.clase_contactoRepository.save(clase_contactoItem));
        }
        return optionalClaseContacto;
    }
}
