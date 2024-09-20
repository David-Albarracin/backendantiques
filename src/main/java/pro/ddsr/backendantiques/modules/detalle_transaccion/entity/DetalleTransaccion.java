
package pro.ddsr.backendantiques.modules.detalle_transaccion.entity;

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
import pro.ddsr.backendantiques.modules.antiguedades.entity.Antiguedades;
import pro.ddsr.backendantiques.modules.transacciones.entity.Transacciones;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="detalle_transaccion")
public class DetalleTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "antiguedad_id")
    @NotNull(message = "No puede ser nulo")
    Antiguedades antiguedadId;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    @NotNull(message = "No puede ser nulo")
    Transacciones transaccionId;

}
