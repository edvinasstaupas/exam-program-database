package lt.staupasedvinas.service.question_services;

import lt.staupasedvinas.entity.Question;

import static lt.staupasedvinas.util.InputUtil.getCharInput;
import static lt.staupasedvinas.util.InputUtil.getStringInput;
import static lt.staupasedvinas.util.PrintUtil.print;

public class QuestionValueAssignationUtil {
    protected static void setValueQuestion(Question question) {
        print("Please enter the question: ");
        question.setQuestion(getStringInput());
    }

    protected static void setValueA(Question question) {
        print("Please enter the answer a: ");
        question.setAnswerA(getStringInput());
    }

    protected static void setValueB(Question question) {
        print("Please enter the answer b: ");
        question.setAnswerB(getStringInput());
    }

    protected static void setValueC(Question question) {
        print("Please enter the answer c: ");
        question.setAnswerC(getStringInput());
    }

    protected static void setValueRightLetter(Question question) {
        print("Please enter the right answer letter: ");
        question.setRightAnswer(getCharInput());
    }
}
