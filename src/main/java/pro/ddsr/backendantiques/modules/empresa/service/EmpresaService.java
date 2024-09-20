
package pro.ddsr.backendantiques.modules.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.empresa.repository.EmpresaRepository;
import pro.ddsr.backendantiques.modules.empresa.entity.Empresa;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Transactional
    public Optional<Empresa> delete(Long id) {
        Optional<Empresa> optionalEmpresa = this.empresaRepository.findById(id);
        optionalEmpresa.ifPresent(
            EmpresaFound -> {
                this.empresaRepository.delete(EmpresaFound);
            }
        );
        return optionalEmpresa;
    }
 
    public List<Empresa> findAll() {
        return (List<Empresa>) this.empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id) {
        return this.empresaRepository.findById(id);
    }

    public Empresa save(Empresa Empresa) {
        return this.empresaRepository.save(Empresa);
    }

    public Optional<Empresa> update(Long id, Empresa empresa) {
        Optional<Empresa> optionalEmpresa = this.empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa empresaItem = optionalEmpresa.orElseThrow();
            // Sets
            return Optional.of(this.empresaRepository.save(empresaItem));
        }
        return optionalEmpresa;
    }
}
