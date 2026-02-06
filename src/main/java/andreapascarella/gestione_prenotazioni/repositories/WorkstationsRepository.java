package andreapascarella.gestione_prenotazioni.repositories;

import andreapascarella.gestione_prenotazioni.entities.Building;
import andreapascarella.gestione_prenotazioni.entities.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationsRepository extends JpaRepository<Workstation, Long> {

    boolean existsByBuildingAndDescription(Building building, String description);

}
