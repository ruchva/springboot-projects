package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_comentarios_solicitud", schema = "lumira")
public class Comentarios {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "fk_usuario")
    private Long idUsuario;
    @Column(name = "id_adjunto")
    private String idAdjunto;
    @Column(name = "glosa_comentario")
    private String glosaComentario;
    @Column(name = "link_comentario")
    private String linkComentario;
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;
    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
