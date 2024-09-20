
package pro.ddsr.backendantiques.modules.antiguedades.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
import pro.ddsr.backendantiques.modules.categorias.entity.Categorias;
import pro.ddsr.backendantiques.modules.epoca_antiguedad.entity.EpocaAntiguedad;
import pro.ddsr.backendantiques.modules.sucursal.entity.Sucursal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="antiguedades")
public class Antiguedades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "text")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "epoca_id")
    @NotNull(message = "No puede ser nulo")
    EpocaAntiguedad epocaId;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "No puede ser nulo")
    Categorias categoriaId;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    @NotNull(message = "No puede ser nulo")
    Sucursal sucursalId;

    @Column(name = "valor_estimado", precision = 10, scale = 2)
    private Integer valorEstimado;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

}
