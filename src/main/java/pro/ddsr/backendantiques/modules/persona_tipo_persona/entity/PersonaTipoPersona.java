
package pro.ddsr.backendantiques.modules.persona_tipo_persona.entity;

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
import pro.ddsr.backendantiques.modules.persona.entity.Persona;
import pro.ddsr.backendantiques.modules.tipo_persona.entity.TipoPersona;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persona_tipo_persona")
public class PersonaTipoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    @NotNull(message = "No puede ser nulo")
    Persona personaId;

    @ManyToOne
    @JoinColumn(name = "tipo_persona_id")
    @NotNull(message = "No puede ser nulo")
    TipoPersona tipoPersonaId;

}
