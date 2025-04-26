package gob.ypfb.lumira.app.solicitudes.repository;

import gob.ypfb.lumira.app.solicitudes.domain.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentarios, Long> {

}
