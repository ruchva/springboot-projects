/* ================================================================
 * El archivo "ForbiddenException" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(StringUtils.isEmpty(detail) ? DESCRIPTION : detail);
    }
}
