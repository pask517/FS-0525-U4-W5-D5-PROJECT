package andreapascarella.gestione_prenotazioni.entities;

import andreapascarella.gestione_prenotazioni.enums.WorkstationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "workstations")
public class Workstation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "workstation_id")
    private long workstationId;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkstationType workstationType;

    @Column(nullable = false)
    private int maxOccupancy;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Workstation(String description, WorkstationType workstationType, int maxOccupancy, Building building) {
        this.description = description;
        this.workstationType = workstationType;
        this.maxOccupancy = maxOccupancy;
        this.building = building;
    }
}
