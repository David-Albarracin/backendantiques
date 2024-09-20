
package pro.ddsr.backendantiques.modules.empleado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backendantiques.modules.empleado.repository.EmpleadoRepository;
import pro.ddsr.backendantiques.modules.empleado.entity.Empleado;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Transactional
    public Optional<Empleado> delete(Long id) {
        Optional<Empleado> optionalEmpleado = this.empleadoRepository.findById(id);
        optionalEmpleado.ifPresent(
            EmpleadoFound -> {
                this.empleadoRepository.delete(EmpleadoFound);
            }
        );
        return optionalEmpleado;
    }
 
    public List<Empleado> findAll() {
        return (List<Empleado>) this.empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        return this.empleadoRepository.findById(id);
    }

    public Empleado save(Empleado Empleado) {
        return this.empleadoRepository.save(Empleado);
    }

    public Optional<Empleado> update(Long id, Empleado empleado) {
        Optional<Empleado> optionalEmpleado = this.empleadoRepository.findById(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleadoItem = optionalEmpleado.orElseThrow();
            // Sets
            return Optional.of(this.empleadoRepository.save(empleadoItem));
        }
        return optionalEmpleado;
    }
}
