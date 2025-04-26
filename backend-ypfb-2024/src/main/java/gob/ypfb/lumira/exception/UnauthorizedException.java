/* ================================================================
 * El archivo "UnauthorizedException" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class UnauthorizedException extends RuntimeException {
    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(StringUtils.isEmpty(detail) ? DESCRIPTION : detail);
    }
}