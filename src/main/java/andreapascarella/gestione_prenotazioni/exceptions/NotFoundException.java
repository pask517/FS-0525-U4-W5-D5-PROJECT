package andreapascarella.gestione_prenotazioni.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("La risorsa con id " + id + " non é stata trovata");
    }
}
