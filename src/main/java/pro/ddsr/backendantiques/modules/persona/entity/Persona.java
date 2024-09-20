
package pro.ddsr.backendantiques.modules.persona.entity;

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
import pro.ddsr.backendantiques.modules.estado_persona.entity.EstadoPersona;
import pro.ddsr.backendantiques.modules.genero.entity.Genero;
import pro.ddsr.backendantiques.modules.sucursal.entity.Sucursal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "estado_persona_id")
    @NotNull(message = "No puede ser nulo")
    EstadoPersona estadoPersona;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    @NotNull(message = "No puede ser nulo")
    Sucursal sucursalId;

    
    @ManyToOne
    @JoinColumn(name = "genero_id")
    @NotNull(message = "No puede ser nulo")
    Genero genero;


}
