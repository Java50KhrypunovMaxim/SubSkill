package subskill.subskill.exception;

@SuppressWarnings("serial")
public class NoUserInRepositoryException extends RuntimeException {
    public NoUserInRepositoryException(String message) {
        super(message);
    }
}
