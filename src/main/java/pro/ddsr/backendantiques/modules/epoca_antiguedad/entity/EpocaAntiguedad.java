
package pro.ddsr.backendantiques.modules.epoca_antiguedad.entity;

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
@Table(name="epoca_antiguedad")
public class EpocaAntiguedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
