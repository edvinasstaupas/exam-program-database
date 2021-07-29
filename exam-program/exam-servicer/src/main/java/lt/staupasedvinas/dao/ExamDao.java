package lt.staupasedvinas.dao;

import dao.AbstractDao;
import lt.staupasedvinas.entity.Exam;
import org.hibernate.Session;

public class ExamDao extends AbstractDao<Exam> {

    public ExamDao(Session session) {
        super(session, Exam.class);
    }
}
