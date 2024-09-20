
package pro.ddsr.backendantiques.modules.sucursal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.sucursal.repository.SucursalRepository;
import pro.ddsr.backendantiques.modules.sucursal.entity.Sucursal;

@Service
public class SucursalService {
    @Autowired
    SucursalRepository sucursalRepository;

    @Transactional
    public Optional<Sucursal> delete(Long id) {
        Optional<Sucursal> optionalSucursal = this.sucursalRepository.findById(id);
        optionalSucursal.ifPresent(
            SucursalFound -> {
                this.sucursalRepository.delete(SucursalFound);
            }
        );
        return optionalSucursal;
    }
 
    public List<Sucursal> findAll() {
        return (List<Sucursal>) this.sucursalRepository.findAll();
    }

    public Optional<Sucursal> findById(Long id) {
        return this.sucursalRepository.findById(id);
    }

    public Sucursal save(Sucursal Sucursal) {
        return this.sucursalRepository.save(Sucursal);
    }

    public Optional<Sucursal> update(Long id, Sucursal sucursal) {
        Optional<Sucursal> optionalSucursal = this.sucursalRepository.findById(id);
        if (optionalSucursal.isPresent()) {
            Sucursal sucursalItem = optionalSucursal.orElseThrow();
            // Sets
            return Optional.of(this.sucursalRepository.save(sucursalItem));
        }
        return optionalSucursal;
    }
}
