package andreapascarella.gestione_prenotazioni.repositories;

import andreapascarella.gestione_prenotazioni.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.workstation.building.buildingId=:buildingId AND r.workstation.workstationId=:workstationId AND r.reservationDate=:reservationDate")
    List<Reservation> filterByBuildingIdAndWorkstationIdAndReservationDate(long buildingId, long workstationId, LocalDate reservationDate);
}
