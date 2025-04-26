/* ================================================================
 * El archivo "ClienteRestRequest" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 24/7/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@AllArgsConstructor
@Data
@Builder
public class ClienteRestRequest implements Serializable {

    private String id;
    private String apiUrl;
    private String accessToken;
    private String bearerToken;
    private String method;
    private Object data;
    private HashMap<String, String> parameters;
    private HashMap<String, String> headers;

    public ClienteRestRequest() {
        this.parameters = new HashMap<>();
        this.headers = new HashMap<>();
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }
}
