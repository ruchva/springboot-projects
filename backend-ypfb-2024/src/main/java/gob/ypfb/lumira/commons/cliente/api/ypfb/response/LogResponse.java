/* ================================================================
 * El archivo "LoggerResponse" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 27/10/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.cliente.api.ypfb.response;

import lombok.Data;

@Data
public class LogResponse {
    private String id;
    private String log;
    private String host;
    private String fecha;
    private String usuario;
    private String level;
    private String dominio;
    private String objetoId;
}
