/* ================================================================
 * El archivo "IDominioRepository" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.domain.repository;

import gob.ypfb.lumira.app.parametricas.domain.model.Dominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDominioRepository extends JpaRepository<Dominio, Long> {

    @Query(value = "SELECT d From Dominio d where d.eliminado = false and (d.codigo like :params OR d.valor like :params) order by d.tipoDominio, d.orden")
    List<Dominio> findByCriterio(@Param("params") String params);

    List<Dominio> findByEliminadoFalse();

    List<Dominio> findAllByTipoDominioAndEliminadoFalse(String tipoDominio);
}
