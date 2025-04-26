/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "MaeResponse.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 24/8/2023 17:32
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal;

import lombok.Data;

@Data
public class MaeResponse {
    public String id;
    public String username;
    public String nombreCompleto;
    public String nombreFormal;
    public CargoResponse cargo;
}
