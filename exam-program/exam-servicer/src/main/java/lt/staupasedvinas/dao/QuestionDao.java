package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.Question;
import org.hibernate.Session;

import java.util.List;

public class QuestionDao extends AbstractDao<Question> {
    public QuestionDao(Session session) {
        super(session, Question.class);
    }

    public List<Question> loadQuestionsForQuestionnaire(Long id) {
        //return session.createQuery("from question q join question_questionnaire qq on q.id = qq.questionnaire_id;", Question.class).list();
        return session.createQuery("from Question", Question.class).list();
    }
}
