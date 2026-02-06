package andreapascarella.gestione_prenotazioni.runners;

import andreapascarella.gestione_prenotazioni.entities.Building;
import andreapascarella.gestione_prenotazioni.entities.User;
import andreapascarella.gestione_prenotazioni.entities.Workstation;
import andreapascarella.gestione_prenotazioni.enums.WorkstationType;
import andreapascarella.gestione_prenotazioni.exceptions.NotFoundException;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.services.BuildingsService;
import andreapascarella.gestione_prenotazioni.services.UsersService;
import andreapascarella.gestione_prenotazioni.services.WorkstationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {

    private final UsersService usersService;
    private final BuildingsService buildingsService;
    private final WorkstationsService workstationsService;

    public MyRunner(UsersService usersService, BuildingsService buildingsService, WorkstationsService workstationsService) {
        this.usersService = usersService;
        this.buildingsService = buildingsService;
        this.workstationsService = workstationsService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            User user1 = new User("andreapask", "Andrea Pascarella", "andrea@v.com");

            //usersService.saveUser(user1);

            User user2 = new User("andreapask", "Andrea Pascarella", "andrea@vasa.com");

            //usersService.saveUser(user2);

            Building ufficioMilano = new Building("BNP Paribas Milano", "Via milano,12", "Milano");

            //buildingsService.saveBuilding(ufficioMilano);

            Building ufficioMilanoFromDB = buildingsService.findBuildingByBuildingId(1);

            Workstation workstation1 = new Workstation("Postazione numero 1", WorkstationType.PRIVATE, 10, ufficioMilanoFromDB);

            //workstationsService.saveWorkstation(workstation1);

        } catch (ValidationException | NotFoundException ex) {
            log.info(ex.getMessage());
        }
    }
}
