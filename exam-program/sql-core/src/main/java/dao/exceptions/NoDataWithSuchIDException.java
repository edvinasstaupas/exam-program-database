package dao.exceptions;

public class NoDataWithSuchIDException extends Exception {
    public NoDataWithSuchIDException(String message) {
        super(message);
    }
}
