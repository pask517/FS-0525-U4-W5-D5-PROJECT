package andreapascarella.gestione_prenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long reservationId;

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    private Workstation workstation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate reservationDate;

    public Reservation(Workstation workstation, User user, int year, int month, int day) {
        this.workstation = workstation;
        this.user = user;
        this.reservationDate = LocalDate.of(year, month, day);
    }
}
