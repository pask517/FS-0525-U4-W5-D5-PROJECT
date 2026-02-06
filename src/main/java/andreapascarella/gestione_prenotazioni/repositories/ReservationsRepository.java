package andreapascarella.gestione_prenotazioni.repositories;

import andreapascarella.gestione_prenotazioni.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

    //boolean existsByWorkstationIdAndReservationDate(Long workstationId, LocalDate reservationDate);
}
