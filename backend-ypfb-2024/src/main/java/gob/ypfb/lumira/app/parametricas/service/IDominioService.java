/* ================================================================
 * El archivo "IDominioService" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.service;

import gob.ypfb.lumira.app.parametricas.domain.model.Dominio;
import gob.ypfb.lumira.app.parametricas.dto.request.DominioRequest;
import gob.ypfb.lumira.app.parametricas.dto.response.DominioResponse;
import gob.ypfb.lumira.exception.NotFoundException;

import java.util.List;


public interface IDominioService {

    Dominio obtenerPorId(Long id) throws NotFoundException;

    List<DominioResponse> obtenerListadoDominios();

    List<DominioResponse> obtenerPorTipoDominio(String tipoDominio);

    Dominio guardar(DominioRequest dominioRequest);

    Dominio actualizar(DominioRequest dominioRequest, Long id);

    void eliminar(Long id);
}
