
package pro.ddsr.backendantiques.modules.contacto_persona.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contacto_persona")
public class ContactoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
