package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_items", schema = "lumira")
public class Items {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "fk_numero_tarifa")
    private Long numeroTarifa;
    @Column(name = "fk_codigo_servicio")
    private String codigoServicio;
    @Column(name = "fk_correlativo_servicio")
    private Long correlativoServicio;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;


    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "precio_base")
    private BigDecimal precioBase;
    @Column(name = "fk_tipo_informacion")
    private Long idTipoInformacion;

}
