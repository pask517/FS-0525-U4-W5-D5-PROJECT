package andreapascarella.gestione_prenotazioni.services;

import andreapascarella.gestione_prenotazioni.entities.Reservation;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.repositories.ReservationsRepository;
import andreapascarella.gestione_prenotazioni.repositories.UsersRepository;
import andreapascarella.gestione_prenotazioni.repositories.WorkstationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationsService {

    private final UsersRepository usersRepository;

    private final WorkstationsRepository workstationsRepository;

    private final ReservationsRepository reservationsRepository;

    public ReservationsService(UsersRepository usersRepository, WorkstationsRepository workstationsRepository, ReservationsRepository reservationsRepository) {
        this.usersRepository = usersRepository;
        this.workstationsRepository = workstationsRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public void saveReservation(Reservation reservation) {
        if (reservationsRepository.filterReservationByBuildingIdAndWorkstationIdAndReservationDate(reservation.getWorkstation().getBuilding().getBuildingId(), reservation.getWorkstation().getWorkstationId(), reservation.getReservationDate()).size() >= reservation.getWorkstation().getMaxOccupancy())
            throw new ValidationException("La postazione é giá completamente occupata");

        if (!reservationsRepository.filterReservationByUserIdAndReservationDate(reservation.getUser().getUserId(), reservation.getReservationDate()).isEmpty())
            throw new ValidationException("L'utente " + reservation.getUser().getUsername() + " ha giá una prenotazione in data: " + reservation.getReservationDate());

        reservationsRepository.save(reservation);

        log.info("La prenotazione alla postazione con descrizione: " + reservation.getWorkstation().getDescription() + " é stata salvata correttamente!");
    }


}
