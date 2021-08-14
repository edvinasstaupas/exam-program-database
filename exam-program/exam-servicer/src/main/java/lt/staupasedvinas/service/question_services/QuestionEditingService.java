package lt.staupasedvinas.service.question_services;

import dao.exceptions.NoDataWithSuchIDException;
import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.dao.QuestionDao;
import lt.staupasedvinas.entity.Question;
import lt.staupasedvinas.util.PrintUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lt.staupasedvinas.service.question_services.QuestionValueAssignationUtil.*;
import static lt.staupasedvinas.util.InputUtil.*;
import static lt.staupasedvinas.util.PrintUtil.print;

@RequiredArgsConstructor
public class QuestionEditingService {

    private final Session session;
    private Question question;

    public void init() {
        QuestionDao questionDao = new QuestionDao(session);
        List<Question> questionList = questionDao.loadAll();
        print("Would you like to see all questions?");
        boolean seeQuestions = getBooleanInput();
        if (seeQuestions) {
            questionList.forEach(q -> print(q.getId() + " " + q.getQuestion()));
        }
        boolean inputIsBadOrNone = true;
        while (inputIsBadOrNone) {
            try {
                print("Please enter question id:");
                Long id = getLongInput();
                Optional<Question> optionalQuestion = questionDao.getByID(id);
                if (optionalQuestion.isEmpty()) {
                    throw new NoDataWithSuchIDException("No question with id: " + id);
                } else {
                    question = optionalQuestion.get();
                    questionDao.saveOrUpdate(getNewValues());
                    print("Question was changed");
                    inputIsBadOrNone = false;
                }
            } catch (NoDataWithSuchIDException e) {
                print(e.getMessage());
            }
        }
    }

    private Question getNewValues() {
        String questionOutput = "Question info: " +
                "\n" + question.getQuestion() +
                "\na.txt. " + question.getAnswerA() +
                "\nb. " + question.getAnswerB() +
                "\nc. " + question.getAnswerC() +
                "\nright answer letter: " + question.getRightAnswer();
        System.out.println((questionOutput));

        print("Would you like to change question?");
        if (getBooleanInput()) {
            setValueQuestion(question);
        }
        print("Would you like to change answer a.txt?");
        if (getBooleanInput()) {
            setValueA(question);
        }
        print("Would you like to change answer b?");
        if (getBooleanInput()) {
            setValueB(question);
        }
        print("Would you like to change answer c?");
        if (getBooleanInput()) {
            setValueC(question);
        }
        print("Would you like to change right answer letter?");
        if (getBooleanInput()) {
            setValueRightLetter(question);
        }
        return question;
    }
}
