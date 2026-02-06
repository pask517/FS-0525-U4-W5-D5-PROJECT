package andreapascarella.gestione_prenotazioni.services;

import andreapascarella.gestione_prenotazioni.entities.Workstation;
import andreapascarella.gestione_prenotazioni.enums.WorkstationType;
import andreapascarella.gestione_prenotazioni.exceptions.NotFoundException;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.repositories.ReservationsRepository;
import andreapascarella.gestione_prenotazioni.repositories.WorkstationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkstationsService {

    private WorkstationsRepository workstationsRepository;

    private ReservationsRepository reservationsRepository;


    public WorkstationsService(WorkstationsRepository workstationsRepository, ReservationsRepository reservationsRepository) {
        this.workstationsRepository = workstationsRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public void saveWorkstation(Workstation workstation) {
        if (workstationsRepository.existsByBuildingAndDescription(workstation.getBuilding(), workstation.getDescription()))
            throw new ValidationException("Un'altra postazione nell'edificio " + workstation.getBuilding().getBuildingName() + " ha una descrizione uguale a questa!");

        workstationsRepository.save(workstation);

        log.info("Postazione con descrizione " + workstation.getDescription() + " salvata correttamente!");
    }

    public Workstation findWorkstationByWorkstationId(long workstationId) {
        return workstationsRepository.findById(workstationId).orElseThrow(() -> new NotFoundException(workstationId));
    }

    public List<Workstation> filterWorkstationByWorkstationTypeAndCity(WorkstationType workstationType, String city) {
        List<Workstation> found = workstationsRepository.filterWorkstationByWorkstationTypeAndCity(workstationType, city);
        if (found.isEmpty())
            throw new NotFoundException("Nessuna postazione corrispondente ai criteri di ricerca richiesti!");
        return found;
    }
}
