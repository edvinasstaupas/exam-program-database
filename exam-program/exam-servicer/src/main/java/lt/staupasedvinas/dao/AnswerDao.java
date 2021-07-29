package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.Answer;
import org.hibernate.Session;

public class AnswerDao extends AbstractDao<Answer> {
    public AnswerDao(Session session) {
        super(session, Answer.class);
    }
}
