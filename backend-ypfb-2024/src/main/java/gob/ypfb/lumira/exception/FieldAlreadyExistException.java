/* ================================================================
 * El archivo "FieldAlreadyExistException" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class FieldAlreadyExistException extends ConflictException {
    private static final String DESCRIPTION = "Field Already Exist";

    public FieldAlreadyExistException(String detail) {
        super(StringUtils.isEmpty(detail) ? DESCRIPTION : detail);
    }
}
