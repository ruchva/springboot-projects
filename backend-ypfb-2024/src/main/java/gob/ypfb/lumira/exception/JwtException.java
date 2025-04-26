/* ================================================================
 * El archivo "JwtException" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class JwtException extends UnauthorizedException {

    private static final String DESCRIPTION = "Jwt exception";

    public JwtException(String detail) {
        super(StringUtils.isEmpty(detail) ? DESCRIPTION : detail);
    }

}