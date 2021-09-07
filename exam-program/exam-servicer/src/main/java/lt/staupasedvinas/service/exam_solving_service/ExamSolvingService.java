package lt.staupasedvinas.service.exam_solving_service;

import dao.exceptions.NoDataWithSuchIDException;
import dao.exceptions.NoDataWithSuchTypeException;
import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.dao.*;
import lt.staupasedvinas.entity.*;
import lt.staupasedvinas.util.InputUtil;
import lt.staupasedvinas.util.PrintUtil;
import lt.staupasedvinas.util.IDGenerator;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.xml.bind.DatatypeConverter.printLong;
import static lt.staupasedvinas.util.InputUtil.*;
import static lt.staupasedvinas.util.PrintUtil.print;

@RequiredArgsConstructor
public class ExamSolvingService {
    private final Session session;
    private final String name;
    private Long questionnaireId;
    private Exam exam;
    private QuestionnaireLog questionnaireLog;
    private Questionnaire questionnaire;

    public void init() {
        Long id = null;
        while (id == null) {
            print("Would you like to see what kind of questionnaire types our program has? Yes/no");
            boolean typeInput = getBooleanInput();
            if (typeInput) {
                getAllTypes();
                print("What type of questionnaire would you like to solve? Please enter type name: ");
                String type = getStringInput();
                try {
                    id = new QuestionnaireDao(session).getByType(type);
                } catch (NoDataWithSuchTypeException e) {
                    //e.printStackTrace();
                    print(e.getMessage());
                    id = null;
                }
            } else {
                print("Then please enter questionnaire id: ");
                id = getLongInput();
            }
            if (id != null) {
                try {
                    questionnaireId = id;
                    startExam();
                } catch (NoDataWithSuchIDException e) {
                    //e.printStackTrace();
                    print(e.getMessage());
                    id = null;
                }
            }
        }
    }

    private void startExam() throws NoDataWithSuchIDException {
        QuestionnaireDao questionnaireDao = new QuestionnaireDao(session);
        Optional<Questionnaire> questionareOptional = questionnaireDao.getByID(questionnaireId);
        print(questionareOptional.toString());
        if (questionareOptional.isEmpty()) {
            throw new NoDataWithSuchIDException("No questionnaire with id " + questionnaireId);
        } else {
            questionnaire = questionareOptional.get();
        }
        solveQuestionare();
    }

    private void solveQuestionare() {
        List<Question> questionList = new QuestionDao(session).
                loadQuestionsForQuestionnaire(questionnaire.getId());
        questionnaireLog = QuestionnaireLog.builder()
                .id(IDGenerator.getQuestionnaireLogId())
                .questionnaire(questionnaire)
                .build();
        new QuestionnaireLogDao(session).save(questionnaireLog);
        List<Answer> answerList = questionList.stream()
                .map(this::solveOneQuestion)
                .collect(Collectors.toList());
        AnswerDao answerDao = new AnswerDao(session);
        answerList.forEach(item -> {
                    item.setQuestionnaireLog(questionnaireLog);
                    //debug
                    printLong(item.getId());
                    answerDao.save(item);
                }
        );
        new ExamDao(session).save(exam = Exam.builder()
                .id(IDGenerator.getExamId())
                .name(name)
                .questionnaireLog(questionnaireLog)
                .build()
        );
    }

    private Answer solveOneQuestion(Question question) {
        String questionOutput = question.getQuestion() +
                "\na.txt. " + question.getAnswerA() +
                "\nb. " + question.getAnswerB() +
                "\nc. " + question.getAnswerC();
        print(questionOutput);
        char letter = InputUtil.getAnswerInput();
        return Answer.builder()
                .id(IDGenerator.getAnswerId())
                .questionnaireLog(questionnaireLog)
                .chosenLetter(letter)
                .isRight((letter == question.getRightAnswer()))
                .build();
    }

    private void getAllTypes() {
        List<String> types = new QuestionnaireDao(session).loadAllDistinctTypes();
        print("Found types: ");
        types.forEach(PrintUtil::print);
    }
}