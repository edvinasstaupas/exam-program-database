package lt.staupasedvinas.service.question_services;

import dao.exceptions.NoDataWithSuchIDException;
import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.dao.QuestionDao;
import lt.staupasedvinas.entity.Question;
import org.hibernate.Session;

import static lt.staupasedvinas.service.question_services.QuestionValueAssignationUtil.*;

@RequiredArgsConstructor
public class QuestionAddingService {

    private final Session session;
    private Question question;

    public void init() {
        QuestionDao questionDao = new QuestionDao(session);
        question = new Question();
        questionDao.save(getNewValues());
    }

    private Question getNewValues() {
        setValueQuestion(question);
        setValueA(question);
        setValueB(question);
        setValueC(question);
        setValueRightLetter(question);
        return question;
    }
}
