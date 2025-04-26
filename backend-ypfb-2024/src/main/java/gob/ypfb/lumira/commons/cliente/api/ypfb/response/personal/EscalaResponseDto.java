/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "EscalaResponseDto.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 6/27/2023 11:48 a. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EscalaResponseDto {
    private Integer nivel;
    private String denominacion;
    private BigDecimal monto;
}
