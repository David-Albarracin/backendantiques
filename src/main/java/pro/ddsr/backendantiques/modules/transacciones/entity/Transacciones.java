
package pro.ddsr.backendantiques.modules.transacciones.entity;

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
import pro.ddsr.backendantiques.modules.persona.entity.Persona;
import pro.ddsr.backendantiques.modules.tipo_transaccion.entity.TipoTransaccion;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transacciones")
public class Transacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    @NotNull(message = "No puede ser nulo")
    Persona personaId;

    @ManyToOne
    @JoinColumn(name = "tipo_transaccion_id")
    @NotNull(message = "No puede ser nulo")
    TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "estacion_pago_id")
    @NotNull(message = "No puede ser nulo")
    EstacionPago estacionPagoId;

}
