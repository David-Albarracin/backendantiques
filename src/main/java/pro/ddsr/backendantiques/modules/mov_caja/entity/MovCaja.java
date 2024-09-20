
package pro.ddsr.backendantiques.modules.mov_caja.entity;

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
import pro.ddsr.backendantiques.modules.estacion_pago.entity.EstacionPago;
import pro.ddsr.backendantiques.modules.tipom_caja.entity.TipomCaja;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mov_caja")
public class MovCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "estacion_pago_id")
    @NotNull(message = "No puede ser nulo")
    EstacionPago estacionPagoId;

    @ManyToOne
    @JoinColumn(name = "tipom_caja_id")
    @NotNull(message = "No puede ser nulo")
    TipomCaja TipomCajaId;
}
