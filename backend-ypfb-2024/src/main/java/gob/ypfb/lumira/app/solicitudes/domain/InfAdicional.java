package gob.ypfb.lumira.app.solicitudes.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Table(name = "trs_informacion_adicional", schema = "lumira")
public class InfAdicional {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_solicitud")
    private Long idSolicitud;
    @Column(name = "fk_tipo_informacion")
    private Long idTipoInformacion;

    @Column(name = "adicional1")
    private String adicional1;
    @Column(name = "adicional2")
    private String adicional2;
    @Column(name = "adicional3")
    private String adicional3;
    @Column(name = "adicional4")
    private String adicional4;
    @Column(name = "adicional5")
    private String adicional5;
    @Column(name = "adicional6")
    private String adicional6;
    @Column(name = "adicional7")
    private String adicional7;
    @Column(name = "comentario")
    private String comentario;

    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @Column(name = "fk_item")
    private Long idItem;

}
