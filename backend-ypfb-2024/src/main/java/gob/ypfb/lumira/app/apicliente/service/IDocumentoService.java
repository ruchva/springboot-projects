/* ================================================================
 * El archivo "IDocumentoService" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 17/8/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.apicliente.service;

import gob.ypfb.lumira.app.apicliente.domain.model.Documento;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.DocumentoDmsRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DmsResponse;

import java.util.List;
import java.util.Optional;

public interface IDocumentoService {

    List<Documento> listar(Optional<String> query);

    Documento obtenerPorId(Long id);

    Documento obtenerPorDmsId(String dmsId);

    Documento guardar(DocumentoDmsRequest dmsRequest, DmsResponse dmsResponse);

    Documento actualizar(Documento documento);

    void eliminar(Long id);

}
