package dao.exceptions;

public class ConfigReadException extends RuntimeException{
    public ConfigReadException(String message) {
        super("There's something wrong with configuration file: " + message);
        System.out.println(this.getMessage());
    }
}
