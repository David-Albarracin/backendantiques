
package pro.ddsr.backendantiques.modules.historial_propiedad.entity;

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
@Table(name="historial_propiedad")
public class HistorialPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
