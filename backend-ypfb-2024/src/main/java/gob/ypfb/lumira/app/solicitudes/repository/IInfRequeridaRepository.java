package gob.ypfb.lumira.app.solicitudes.repository;

import gob.ypfb.lumira.app.solicitudes.domain.InfRequerida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInfRequeridaRepository extends JpaRepository<InfRequerida, Long> {

}
