package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_informacion_requerida", schema = "lumira")
public class InfRequerida {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "fk_tipo_iformacion")
    private Long idTipoInformacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "area")
    private String area;
    @Column(name = "formato")
    private String formato;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

}
