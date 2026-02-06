package andreapascarella.gestione_prenotazioni.services;

import andreapascarella.gestione_prenotazioni.entities.User;
import andreapascarella.gestione_prenotazioni.exceptions.NotFoundException;
import andreapascarella.gestione_prenotazioni.exceptions.ValidationException;
import andreapascarella.gestione_prenotazioni.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(User newUser) {
        if (usersRepository.existsByEmail(newUser.getEmail()))
            throw new ValidationException("Email " + newUser.getEmail() + " già presente!");

        if (usersRepository.existsByUsername(newUser.getUsername()))
            throw new ValidationException("Utente con username " + newUser.getUsername() + " già presente!");

        if (!newUser.getEmail().contains("@") && !newUser.getEmail().contains("."))
            throw new ValidationException("Email non valida!");

        if (newUser.getFullName().length() < 3)
            throw new ValidationException("Il nome completo non puó essere piú corto di 3 caratteri!");

        if (newUser.getUsername().length() < 3)
            throw new ValidationException("L'username non puó essere piú corto di 3 caratteri!");

        this.usersRepository.save(newUser);

        log.info("L' utente " + newUser.getUsername() + " é stato salvato correttamente!");
    }

    public User findUserByUserId(long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }
}
