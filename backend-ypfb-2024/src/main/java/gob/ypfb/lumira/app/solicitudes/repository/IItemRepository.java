package gob.ypfb.lumira.app.solicitudes.repository;

import gob.ypfb.lumira.app.solicitudes.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Items, Long> {

}
