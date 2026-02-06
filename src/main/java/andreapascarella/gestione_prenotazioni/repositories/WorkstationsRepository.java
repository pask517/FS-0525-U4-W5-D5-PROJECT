package andreapascarella.gestione_prenotazioni.repositories;

import andreapascarella.gestione_prenotazioni.entities.Building;
import andreapascarella.gestione_prenotazioni.entities.Workstation;
import andreapascarella.gestione_prenotazioni.enums.WorkstationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkstationsRepository extends JpaRepository<Workstation, Long> {

    boolean existsByBuildingAndDescription(Building building, String description);

    @Query("SELECT w from Workstation w WHERE w.workstationType=:workstationType AND LOWER(w.building.city)=LOWER(:city)")
    List<Workstation> filterWorkstationByWorkstationTypeAndCity(WorkstationType workstationType, String city);

}
