package andreapascarella.gestione_prenotazioni.services;

import andreapascarella.gestione_prenotazioni.entities.Workstation;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.repositories.ReservationsRepository;
import andreapascarella.gestione_prenotazioni.repositories.WorkstationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    //public boolean isWorkstationFree(Long workstationId, LocalDate reservationDate) {
    //  return reservationsRepository.existsByWorkstationIdAndReservationDate(workstationId, reservationDate);
    // }
}
