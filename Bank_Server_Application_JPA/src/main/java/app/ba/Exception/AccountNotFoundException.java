package app.ba.Exception;
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return getMessage();
    }
}
