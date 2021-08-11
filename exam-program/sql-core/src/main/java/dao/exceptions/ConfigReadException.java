package dao.exceptions;

public class ConfigReadException extends RuntimeException{
    public ConfigReadException(String message) {
        super(message);
        System.out.println(message);
    }
}
