package andreapascarella.gestione_prenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long buildingId;

    @Column(nullable = false)
    private String buildingName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    public Building(String buildingName, String address, String city) {
        this.buildingName = buildingName;
        this.address = address;
        this.city = city;
    }
}
