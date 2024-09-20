
package pro.ddsr.backendantiques.modules.tipo_direccion.entity;

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
@Table(name="tipo_direccion")
public class TipoDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
