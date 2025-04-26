package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_asignaciones", schema = "lumira")
public class Asignaciones {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "fk_usuario")
    private Long idUsuario;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
    @Column(name = "fecha_cierre")
    private Timestamp fechaCierre;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fk_estado_asignacion")
    private Long idEstadoAsignacion;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;

}
