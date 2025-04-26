/* ================================================================
 * El archivo "Dominio" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "par_dominio", schema = "lumira")
public class Dominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_dominio")
    private String tipoDominio;
    private String codigo;
    private String valor;
    private int orden;

    private Boolean eliminado;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "usuario_aplicacion")
    private String usuarioAplicacion;
}
