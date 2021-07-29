package lt.staupasedvinas.dao;

import lt.staupasedvinas.entity.*;
import lt.staupasedvinas.hibernate.ConfigCreatingService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoManager {
    private static SessionFactory getSessionFactory() {
        Configuration cfg = ConfigCreatingService.initPostgresConfig();
        cfg.addAnnotatedClass(Question.class);
        cfg.addAnnotatedClass(Questionnaire.class);
        cfg.addAnnotatedClass(QuestionnaireLog.class);
        cfg.addAnnotatedClass(Exam.class);
        cfg.addAnnotatedClass(Answer.class);

        return cfg.buildSessionFactory();
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
