package app.ba.exception;
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return getMessage();
    }

}
