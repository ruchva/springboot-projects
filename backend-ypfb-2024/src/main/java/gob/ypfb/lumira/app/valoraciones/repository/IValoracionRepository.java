package gob.ypfb.lumira.app.valoraciones.repository;

import gob.ypfb.lumira.app.valoraciones.domain.Valoraciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IValoracionRepository extends JpaRepository<Valoraciones, Long> {

}
