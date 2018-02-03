package ua.nure.perets.SummaryTask4.exeption;

public class DBException extends AppException {

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(String message) {
        super(message);
    }
}
