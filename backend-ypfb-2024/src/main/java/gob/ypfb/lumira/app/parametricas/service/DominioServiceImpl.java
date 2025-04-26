/* ================================================================
 * El archivo "DominioServiceImpl" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.service;

import gob.ypfb.lumira.app.parametricas.domain.model.Dominio;
import gob.ypfb.lumira.app.parametricas.domain.repository.IDominioRepository;
import gob.ypfb.lumira.app.parametricas.dto.request.DominioRequest;
import gob.ypfb.lumira.app.parametricas.dto.response.DominioResponse;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import gob.ypfb.lumira.utils.ListSorterUtil;
import gob.ypfb.lumira.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DominioServiceImpl implements IDominioService {

    @Autowired
    private JwtServiceContext jwtServiceContext;

    @Autowired
    private IDominioRepository dominioRepository;

/*
    public DominioServiceImpl(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
*/

    @Override
    public List<DominioResponse> obtenerListadoDominios() {
        List<DominioResponse> lista = MapperUtil.mapList(dominioRepository.findByEliminadoFalse(), DominioResponse.class);

        Comparator<DominioResponse> ordenComparator = Comparator.comparing(DominioResponse::getOrden);
        List<Comparator<DominioResponse>> comparators = List.of(ordenComparator);

        ListSorterUtil.sortByAttributes(lista, comparators);

        return lista;
    }

    @Override
    public List<DominioResponse> obtenerPorTipoDominio(String tipoDominio) {
        List<DominioResponse> lista = MapperUtil.mapList(dominioRepository.findAllByTipoDominioAndEliminadoFalse(tipoDominio), DominioResponse.class);

        List<Comparator<DominioResponse>> comparators = List.of(Comparator.comparing(DominioResponse::getOrden));
        ListSorterUtil.sortByAttributes(lista, comparators);

        return lista;
    }

    @Override
    public Dominio obtenerPorId(Long id) throws NotFoundException {
        Optional<Dominio> dominio = dominioRepository.findById(id);

        if (dominio.isEmpty())
            throw new NotFoundException("Dominio con ID : " + id + " ¡No encontrado!");
        return dominio.get();
    }

    @Override
    @Transactional
    public Dominio guardar(DominioRequest dominioRequest) {
        String username = jwtServiceContext.getJwtTokenContextAplicacion().getUsername();
        Dominio dominio = new Dominio();
        dominio.setTipoDominio(dominioRequest.getTipoDominio());
        dominio.setCodigo(dominioRequest.getCodigo());
        dominio.setValor(dominioRequest.getValor());
        dominio.setOrden(dominioRequest.getOrden());

        dominio.setEliminado(Boolean.FALSE);
        dominio.setFechaRegistro(Timestamp.from(Instant.now()));
        dominio.setUsuarioAplicacion(username);
        return dominioRepository.saveAndFlush(dominio);
    }

    @Override
    @Transactional
    public Dominio actualizar(DominioRequest dominioRequest, Long id) {
        Dominio dominio = obtenerPorId(id);
        dominio.setTipoDominio(dominioRequest.getTipoDominio());
        dominio.setCodigo(dominioRequest.getCodigo());
        dominio.setValor(dominioRequest.getValor());
        dominio.setOrden(dominioRequest.getOrden());

        return dominioRepository.saveAndFlush(dominio);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Dominio dominio = obtenerPorId(id);
        dominio.setEliminado(Boolean.TRUE);
        dominioRepository.saveAndFlush(dominio);
    }


}
