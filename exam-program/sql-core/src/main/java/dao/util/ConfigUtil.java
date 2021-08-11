package dao.util;

import dao.exceptions.ConfigReadException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigUtil {
    public static Map<String, String> readConfig() {
        Map<String, String> map = new HashMap<>();
        try (InputStream is = ConfigUtil.class.getResourceAsStream("/config.properties");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {
            String line;
            while ((line = reader.readLine()) != null)
                map.put(line.split("=")[0], line.split("=")[1]);
        } catch (IOException e) {
            throw new ConfigReadException(e.getMessage());
        } catch (NullPointerException e) {
            throw new ConfigReadException("file not found");
        }
        return map;
    }
}
