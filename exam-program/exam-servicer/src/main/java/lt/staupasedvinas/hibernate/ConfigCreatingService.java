package lt.staupasedvinas.hibernate;

import org.hibernate.cfg.Configuration;

public class ConfigCreatingService {
    public static Configuration initPostgresConfig() {
        var cfg = new Configuration();
        //TODO padaryti is external file
        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/exam_db");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        cfg.setProperty("hibernate.connection.username", "postgres");
        cfg.setProperty("hibernate.connection.password", "edvinas");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");
        return cfg;
    }
}
