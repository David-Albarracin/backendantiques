
package pro.ddsr.backendantiques.modules.clase_contacto.entity;

import jakarta.persistence.Column;
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
@Table(name="clase_contacto")
public class ClaseContacto {

    @Id
     @Column(name = "clase_contacto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
