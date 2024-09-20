
package pro.ddsr.backendantiques.modules.sucursal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backendantiques.modules.ciudades.entity.Ciudades;
import pro.ddsr.backendantiques.modules.empresa.entity.Empresa;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @NotNull(message = "No puede ser nulo")
    Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    @NotNull(message = "No puede ser nulo")
    Ciudades ciudadId;
}
