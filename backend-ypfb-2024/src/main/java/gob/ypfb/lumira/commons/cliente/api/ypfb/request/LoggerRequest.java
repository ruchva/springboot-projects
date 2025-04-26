/* ================================================================
 * El archivo "Logger" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 13/7/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoggerRequest {
    private String level;
    private String dominio;
    private String host;
    private String usuario;
    private String objetoId;
    private String log;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss", timezone = "America/La_Paz")
    private Date fecha;


}


