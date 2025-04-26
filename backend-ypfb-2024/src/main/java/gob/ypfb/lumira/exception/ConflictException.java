/* ================================================================
 * El archivo "ConflictException" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String detail) {
        super(StringUtils.isEmpty(detail) ? DESCRIPTION : detail);
    }
}
