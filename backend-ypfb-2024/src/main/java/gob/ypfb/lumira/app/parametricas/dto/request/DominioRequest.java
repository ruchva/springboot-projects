/* ================================================================
 * El archivo "DominioRequest" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 25/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DominioRequest {
    private Long id;

    @JsonProperty("tipo_dominio")
    @NotEmpty
    private String tipoDominio;
    @NotEmpty
    private String codigo;
    @NotEmpty
    private String valor;

    @Min(value = 1, message = "debe tener un valor minimo de 1")
    private int orden;
}
