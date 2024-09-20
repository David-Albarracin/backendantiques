
package pro.ddsr.backendantiques.modules.coleccionistas.entity;

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

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="coleccionistas")
public class Coleccionistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

     @ManyToOne
    @JoinColumn(name = "persona_id")
    @NotNull(message = "No puede ser nulo")
    Persona personaId;

}
