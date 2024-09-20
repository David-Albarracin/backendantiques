
package pro.ddsr.backendantiques.modules.transaccion_medio_pago.entity;

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
import pro.ddsr.backendantiques.modules.medio_pago.entity.MedioPago;
import pro.ddsr.backendantiques.modules.transacciones.entity.Transacciones;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transaccion_medio_pago")
public class TransaccionMedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    @NotNull(message = "No puede ser nulo")
    Transacciones transaccionId;

    @ManyToOne
    @JoinColumn(name = "medio_pago_id")
    @NotNull(message = "No puede ser nulo")
    MedioPago medioPagoId;

}
