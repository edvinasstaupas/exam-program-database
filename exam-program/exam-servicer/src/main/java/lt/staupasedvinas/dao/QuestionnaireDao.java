package lt.staupasedvinas.dao;

import dao.AbstractDao;
import dao.exceptions.NoDataWithSuchTypeException;
import lt.staupasedvinas.entity.Questionnaire;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.util.List;
import java.util.Random;

public class QuestionnaireDao extends AbstractDao<Questionnaire> {
    public QuestionnaireDao(Session session) {
        super(session, Questionnaire.class);
    }

    public List<String> loadAllDistinctTypes() {
        return session.createQuery("select distinct type from Questionnaire", String.class).list();
    }

    public Long getByType(String type) throws NoDataWithSuchTypeException {
        Query<Long> idQuery = session
                .createQuery(
                        "select q.id from Questionnaire as q where q.type = :query_type", Long.class);
        idQuery.setParameter("query_type", type);
        List<Long> idList = idQuery.getResultList();
        if (idList.size() > 0)
            return idList.get(new Random().nextInt(idList.size()));
        else
            throw new NoDataWithSuchTypeException("No questionnaire found with such type: " + type);
    }
}
