package gob.ypfb.lumira.app.solicitudes.repository;

import gob.ypfb.lumira.app.solicitudes.domain.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitudes, Long> {

}
