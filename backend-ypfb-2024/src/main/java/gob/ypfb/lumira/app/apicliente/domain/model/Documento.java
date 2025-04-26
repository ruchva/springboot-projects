/* =========================================================================
 * El archivo "Documento" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 17/8/23
 * @copyright: YPFB
 * =========================================================================
 */

package gob.ypfb.lumira.app.apicliente.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cli_documento", schema = "lumira")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_dms")
    @JsonProperty("id_dms")
    private String dmsId;

    private String dms;

    private String nombre;
    private String descripcion;
    private int orden;
    private Boolean privado;
    private Boolean eliminado;
    //private String privacidad;


    @Column(name = "fecha_registro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/La_Paz")
    @JsonProperty("fecha_registro")
    private Date fechaRegistro;

    @Column(name = "usuario_aplicacion")
    @JsonProperty("usuario_aplicacion")
    private String usuarioAplicacion;
}
