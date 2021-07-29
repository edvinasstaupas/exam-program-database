package lt.staupasedvinas.service.exam_solving_service;

import dao.exceptions.NoDataWithSuchIDException;
import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.dao.*;
import lt.staupasedvinas.entity.*;
import lt.staupasedvinas.util.PrintUtil;
import lt.staupasedvinas.util.IDGenerator;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExamSolvingService {
    private final Session session;
    private final String name;
    private final Long questionnaireId;
    private Exam exam;
    private QuestionnaireLog questionnaireLog;
    private Questionnaire questionnaire;

    public void startExam() throws NoDataWithSuchIDException {
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
                    print(item.getId());
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
                "\na. " + question.getAnswerA() +
                "\nb. " + question.getAnswerB() +
                "\nc. " + question.getAnswerC();
        print(questionOutput);
        char letter = getAnswerInput();
        return Answer.builder()
                .id(IDGenerator.getAnswerId())
                .questionnaireLog(questionnaireLog)
                .chosenLetter(letter)
                .isRight((letter == question.getRightAnswer()))
                .build();
    }

    private char getAnswerInput() {
        Scanner scanner = new Scanner(System.in);
        String answerInput = "";
        while (true) {
            answerInput = scanner.next();
            if (answerInput.equals("a") || answerInput.equals("b") || answerInput.equals("c"))
                return answerInput.toCharArray()[0];
            else
                badInput();
        }
    }

    private void badInput() {
        PrintUtil.printBadInput();
    }

    private void print(String s) {
        PrintUtil.print(s);
    }

    private void print(Long l) {
        PrintUtil.print(String.valueOf(l));
    }

    private void print(int i) {
        PrintUtil.print(String.valueOf(i));
    }
}