/* ================================================================
 * El archivo "Propiedad" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 18/8/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.cliente.api.ypfb.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Propiedad implements Serializable {
    private String nombre;
    private String valor;
}
