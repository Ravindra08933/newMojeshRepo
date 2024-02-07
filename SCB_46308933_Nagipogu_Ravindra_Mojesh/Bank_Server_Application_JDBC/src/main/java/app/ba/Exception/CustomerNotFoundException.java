package app.ba.Exception;
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return getMessage();
    }
}
