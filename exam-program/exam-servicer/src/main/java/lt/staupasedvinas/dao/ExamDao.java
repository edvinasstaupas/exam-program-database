package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.Exam;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ExamDao extends AbstractDao<Exam> {

    public ExamDao(Session session) {
        super(session, Exam.class);
    }

    public List<Exam> loadAllWithName(String name) {
        Query<Exam> query = session.
                createQuery("from Exam as e WHERE e.name = :name", Exam.class);
        query.setParameter("name", name);
        return query.list();
    }
}
