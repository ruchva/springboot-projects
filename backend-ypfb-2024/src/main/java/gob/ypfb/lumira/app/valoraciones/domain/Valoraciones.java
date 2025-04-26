package gob.ypfb.lumira.app.valoraciones.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_valoraciones", schema = "lumira")
public class Valoraciones {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "estado_valoracion")
    private String estadoValoracion;
    @Column(name = "fk_tipo_informacion")
    private Long idTipoInformacion;
    @Column(name = "fk_descuento")
    private Long idDescuento;
    @Column(name = "calculo_base")
    private BigDecimal calculoBase;
    @Column(name = "calculo_descuento")
    private BigDecimal calculoDescuento;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;


}
