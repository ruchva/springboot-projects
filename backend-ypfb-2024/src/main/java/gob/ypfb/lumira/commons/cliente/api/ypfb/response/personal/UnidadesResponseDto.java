/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "UnidadesResponseDto.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/6/2023 9:19 a. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal;

import lombok.Data;

@Data
public class UnidadesResponseDto {
    public String id;
    public String idPredecesor;
    public String sigla;
    public String nombreUnidad;
    public String tipoUnidad;
    public String idPuestoAutoridad;

}
