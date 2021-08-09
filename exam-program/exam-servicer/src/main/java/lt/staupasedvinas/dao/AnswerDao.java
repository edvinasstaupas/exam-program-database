package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.Answer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AnswerDao extends AbstractDao<Answer> {
    public AnswerDao(Session session) {
        super(session, Answer.class);
    }

    public List<Answer> loadAllWithName(String name) {
        System.out.println("BZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        Query<Answer> query = session.
                createQuery("select a FROM Answer AS a " +
                        "JOIN QuestionnaireLog as ql " +
                        "on a.questionnaireLog = ql.id " +
                        "JOIN Exam as e on ql.id = e.id " +
                        "WHERE e.name = :name", Answer.class);
        query.setParameter("name", name);
        return query.list();
    }
}
