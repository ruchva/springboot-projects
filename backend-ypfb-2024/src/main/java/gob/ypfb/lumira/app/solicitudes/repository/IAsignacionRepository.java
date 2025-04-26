package gob.ypfb.lumira.app.solicitudes.repository;

import gob.ypfb.lumira.app.solicitudes.domain.Asignaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsignacionRepository extends JpaRepository<Asignaciones, Long> {

}
