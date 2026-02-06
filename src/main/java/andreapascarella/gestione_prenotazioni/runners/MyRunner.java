package andreapascarella.gestione_prenotazioni.runners;

import andreapascarella.gestione_prenotazioni.entities.Building;
import andreapascarella.gestione_prenotazioni.entities.Reservation;
import andreapascarella.gestione_prenotazioni.entities.User;
import andreapascarella.gestione_prenotazioni.entities.Workstation;
import andreapascarella.gestione_prenotazioni.enums.WorkstationType;
import andreapascarella.gestione_prenotazioni.exceptions.NotFoundException;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.services.BuildingsService;
import andreapascarella.gestione_prenotazioni.services.ReservationsService;
import andreapascarella.gestione_prenotazioni.services.UsersService;
import andreapascarella.gestione_prenotazioni.services.WorkstationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {

    private final UsersService usersService;
    private final BuildingsService buildingsService;
    private final WorkstationsService workstationsService;
    private final ReservationsService reservationsService;

    public MyRunner(UsersService usersService, BuildingsService buildingsService, WorkstationsService workstationsService, ReservationsService reservationsService) {
        this.usersService = usersService;
        this.buildingsService = buildingsService;
        this.workstationsService = workstationsService;
        this.reservationsService = reservationsService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            User user1 = new User("andreapask", "Andrea Pascarella", "andrea@v.com");

            //usersService.saveUser(user1);

            User user2 = new User("gianni92", "Gianni Porzio", "gianni@porz.com");

            //usersService.saveUser(user2);

            //Building ufficioMilano = new Building("BNP Paribas Milano", "Via milano,12", "Milano");

            //buildingsService.saveBuilding(ufficioMilano);

            Building ufficioMilanoFromDB = buildingsService.findBuildingByBuildingId(1);

            //Building ufficioRoma = new Building("BLS Roma", "Via Roma 21", "Roma");

            //buildingsService.saveBuilding(ufficioRoma);

            Building ufficioRomaFromDB = buildingsService.findBuildingByBuildingId(2);

            Workstation workstation1Milano = new Workstation("Postazione numero 1 Milano", WorkstationType.PRIVATE, 10, ufficioMilanoFromDB);

            //workstationsService.saveWorkstation(workstation1Milano);

            Workstation workstation2Milano = new Workstation("Postazione numero 2 Milano", WorkstationType.OPEN_SPACE, 5, ufficioMilanoFromDB);

            //workstationsService.saveWorkstation(workstation2Milano);

            Workstation workstation3Milano = new Workstation("Postazione numero 3 Milano", WorkstationType.MEETING_HALL, 2, ufficioMilanoFromDB);

            //workstationsService.saveWorkstation(workstation3Milano);

            Workstation workstation4Milano = new Workstation("Postazione numero 4 Milano", WorkstationType.OPEN_SPACE, 2, ufficioMilanoFromDB);

            //workstationsService.saveWorkstation(workstation4Milano);

            Workstation workstation5Roma = new Workstation("Postazione numero 5 Roma", WorkstationType.PRIVATE, 3, ufficioRomaFromDB);

            //workstationsService.saveWorkstation(workstation5Roma);

            Workstation workstation6Roma = new Workstation("Postazione numero 6 Roma", WorkstationType.PRIVATE, 1, ufficioRomaFromDB);

            //workstationsService.saveWorkstation(workstation6Roma);

            Workstation workstation7Roma = new Workstation("Postazione numero 7 Roma", WorkstationType.OPEN_SPACE, 7, ufficioRomaFromDB);

            //workstationsService.saveWorkstation(workstation7Roma);

            Workstation workstation8Roma = new Workstation("Postazione numero 8 Roma", WorkstationType.MEETING_HALL, 6, ufficioRomaFromDB);

            //workstationsService.saveWorkstation(workstation8Roma);

            Workstation workstation1MilanoFromDB = workstationsService.findWorkstationByWorkstationId(1);
            Workstation workstation2MilanoFromDB = workstationsService.findWorkstationByWorkstationId(2);
            Workstation workstation3MilanoFromDB = workstationsService.findWorkstationByWorkstationId(3);
            Workstation workstation4MilanoFromDB = workstationsService.findWorkstationByWorkstationId(4);
            Workstation workstation5RomaFromDB = workstationsService.findWorkstationByWorkstationId(5);
            Workstation workstation6RomaFromDB = workstationsService.findWorkstationByWorkstationId(6);
            Workstation workstation7RomaFromDB = workstationsService.findWorkstationByWorkstationId(7);
            Workstation workstation8RomaFromDB = workstationsService.findWorkstationByWorkstationId(8);

            User user1FromDB = usersService.findUserByUserId(1);

            User user2FromDB = usersService.findUserByUserId(2);

            Reservation res1 = new Reservation(workstation1MilanoFromDB, user1FromDB, 2026, 10, 3);

            Reservation res2 = new Reservation(workstation2MilanoFromDB, user2FromDB, 2026, 8, 29);

            //reservationsService.saveReservation(res1);

            //reservationsService.saveReservation(res2);

            List<Workstation> postazioniAMilano = workstationsService.filterWorkstationByWorkstationTypeAndCity(WorkstationType.OPEN_SPACE, "milano");

            log.info(postazioniAMilano.toString());

        } catch (ValidationException | NotFoundException ex) {
            log.info(ex.getMessage());
        }
    }
}
