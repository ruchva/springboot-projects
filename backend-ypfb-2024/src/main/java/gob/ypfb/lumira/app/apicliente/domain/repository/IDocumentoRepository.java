/* ================================================================
 * El archivo "IDocumentoRepository" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 17/8/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.app.apicliente.domain.repository;

import gob.ypfb.lumira.app.apicliente.domain.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDocumentoRepository extends JpaRepository<Documento, Long> {

    @Query(value = "SELECT d FROM Documento d WHERE d.eliminado = false AND ( upper(d.nombre) like :query OR upper(d.descripcion) like :query)")
    List<Documento> findAllByQuery(@Param("query") String query);

    List<Documento> findAllByEliminado(boolean eliminado);

    Optional<Documento> findByDmsId(String dmsId);
}
