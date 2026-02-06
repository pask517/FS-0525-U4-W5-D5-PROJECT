package andreapascarella.gestione_prenotazioni.services;

import andreapascarella.gestione_prenotazioni.entities.Building;
import andreapascarella.gestione_prenotazioni.exceptions.NotFoundException;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.repositories.BuildingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuildingsService {

    private final BuildingsRepository buildingsRepository;

    public BuildingsService(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    public void saveBuilding(Building building) {
        if (buildingsRepository.existsByBuildingName(building.getBuildingName()))
            throw new ValidationException("L'edificio " + building.getBuildingName() + " é giá esistente!");

        buildingsRepository.save(building);

        log.info("L'edificio " + building.getBuildingName() + " é stato salvato correttamente!");
    }

    public Building findBuildingByBuildingId(long buildingId) {
        return buildingsRepository.findById(buildingId).orElseThrow(() -> new NotFoundException(buildingId));
    }
}
