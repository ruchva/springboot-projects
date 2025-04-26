/* ================================================================
 * El archivo "YpfbService" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 27/10/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.databind.JsonNode;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import gob.ypfb.lumira.exception.BadRequestException;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.exception.YpfbServiceException;

public class YpfbService {

    public JsonNode getJsonNode(ResponseRest responseRest) {
        if (responseRest == null)
            throw new YpfbServiceException("Ser. Documento. Respuesta del servidor del gestor documental desconocido.");
        if (responseRest.getCode() >= 500)
            throw new YpfbServiceException("Ser. Documento " + responseRest.getMessage());
        if (responseRest.getCode() == 400)
            throw new BadRequestException("Ser. Documento " + responseRest.getMessage());
        if (responseRest.getCode() == 404)
            throw new NotFoundException("Ser. Documento " + responseRest.getMessage());

        return responseRest.getData();

    }
}
