package exception;

public class ControlException extends RuntimeException{
    public ControlException() {
    }

    public ControlException(String message) {
        super(message);
    }

    public ControlException(Throwable cause) {
        super(cause);
    }
}
