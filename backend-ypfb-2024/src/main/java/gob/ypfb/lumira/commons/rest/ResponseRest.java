/* ================================================================
 * El archivo "YpfbEmail" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 12/7/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.commons.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class ResponseRest implements Serializable {
    private String message;
    private Integer code;
    private JsonNode data;
    private JsonNode errorList; // LinkedHashMap or ArrayList
    private Boolean success;
    private String error;

    public ResponseRest() {
        this.error = "";
    }

    public ResponseRest(Boolean success, Integer code, String message, JsonNode data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = "";
    }
}
