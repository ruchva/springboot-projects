/* ================================================================
 * El archivo "DocumentoService" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 17/8/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.apicliente.service;

import gob.ypfb.lumira.app.apicliente.domain.model.Documento;
import gob.ypfb.lumira.app.apicliente.domain.repository.IDocumentoRepository;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.DocumentoDmsRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DmsResponse;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import gob.ypfb.lumira.utils.string.FormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DocumentoServiceImpl implements IDocumentoService {

    @Autowired
    private IDocumentoRepository documentoRepository;

    @Autowired
    private JwtServiceContext jwtServiceContext;

    @Override
    public List<Documento> listar(Optional<String> query) {
        if (query.isPresent())
            return documentoRepository.findAllByQuery(FormatUtils.likeString(query.get()));

        return documentoRepository.findAllByEliminado(false);
    }

    @Override
    public Documento obtenerPorId(Long id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        if (!documento.isPresent())
            throw new NotFoundException("Documento con ID: " + id + " ¡No encontrado!");

        return documento.get();
    }

    @Override
    public Documento obtenerPorDmsId(String dmsId) {
        Optional<Documento> documento = documentoRepository.findByDmsId(dmsId);
        if (!documento.isPresent())
            throw new NotFoundException("Documento con DMS_ID: " + dmsId + " ¡No encontrado!");

        return documento.get();
    }

    @Override
    @Transactional
    public Documento guardar(DocumentoDmsRequest dmsRequest, DmsResponse dmsResponse) {
        String username = jwtServiceContext.getJwtTokenContextAplicacion().getUsername();

        Documento documento = new Documento();
        documento.setEliminado(Boolean.FALSE);
        documento.setDmsId(dmsResponse.getId());
        documento.setDms(dmsResponse.getDms());
        documento.setNombre(dmsRequest.getName());
        documento.setDescripcion("");
        documento.setOrden(1);
        documento.setPrivado(Boolean.TRUE);
        documento.setFechaRegistro(new Date());
        documento.setUsuarioAplicacion(username);

        return documentoRepository.saveAndFlush(documento);
    }

    @Override
    @Transactional
    public Documento actualizar(Documento documentoRequest) {
        Documento documentoActualizado = obtenerPorId(documentoRequest.getId());

        documentoActualizado.setPrivado(documentoRequest.getPrivado());
        documentoActualizado.setOrden(documentoRequest.getOrden());
        documentoActualizado.setDescripcion(documentoRequest.getDescripcion());

        return documentoRepository.saveAndFlush(documentoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Documento documento = obtenerPorId(id);

        documento.setEliminado(Boolean.TRUE);
        documentoRepository.saveAndFlush(documento);
    }
}
