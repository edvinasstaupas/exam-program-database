package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.QuestionnaireLog;
import org.hibernate.Session;

public class QuestionnaireLogDao extends AbstractDao<QuestionnaireLog> {
    public QuestionnaireLogDao(Session session) {
        super(session, QuestionnaireLog.class);
    }
}
