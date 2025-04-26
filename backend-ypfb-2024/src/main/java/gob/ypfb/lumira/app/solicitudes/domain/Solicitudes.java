package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;

import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_solicitudes", schema = "lumira")
public class Solicitudes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_usuario_solicitante")
    private Long idUsuarioSolicitante;
    @Column(name = "descripcion")
    private String descripcion ;
    @Column(name = "fk_estado_actual")
    private Long idEstadoActual;
    @Column(name = "fk_usuario_responsable")
    private Long idUsuarioResponsable;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
