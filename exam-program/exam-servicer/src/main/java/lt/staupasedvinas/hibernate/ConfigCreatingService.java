package lt.staupasedvinas.hibernate;

import dao.util.ConfigUtil;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class ConfigCreatingService {
    public static Configuration initPostgresConfig() {
        var cfg = new Configuration();
        Map<String, String> properties = ConfigUtil.readConfig();
        for (String key : properties.keySet())
            cfg.setProperty(key, properties.get(key));
        return cfg;
    }
}
