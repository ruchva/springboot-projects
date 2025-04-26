/* ================================================================
 * El archivo "DominioRequest" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DominioResponse {
    private Long id;
    @JsonProperty("tipo_dominio")
    private String tipoDominio;
    private String codigo;
    private String valor;
    private int orden;
    private Boolean eliminado;

    public List<DominioResponse> ordenar(List<DominioResponse> lista) {
        lista.sort((a, b) -> {
            return a.getOrden() - b.getOrden();
        });

        return lista;
    }
}
