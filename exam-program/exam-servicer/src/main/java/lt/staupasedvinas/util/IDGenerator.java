package lt.staupasedvinas.util;

import lt.staupasedvinas.dao.*;
import org.hibernate.Session;

public class IDGenerator {
    private static Long ANSWER_ID;

    private static Long QUESTION_ID;

    private static Long EXAM_ID;

    private static Long QUESTIONNAIRE_ID;

    private static Long QUESTIONNAIRE_LOG_ID;

    public static Long getAnswerId() {
        return ANSWER_ID++;
    }

    public static Long getQuestionId() {
        return QUESTION_ID++;
    }

    public static Long getExamId() {
        return EXAM_ID++;
    }

    public static Long getQuestionnaireId() {
        return QUESTIONNAIRE_ID++;
    }

    public static Long getQuestionnaireLogId() {
        return QUESTIONNAIRE_LOG_ID++;
    }

    private static void setAnswerId(Long answerId) {
        ANSWER_ID = answerId;
    }

    private static void setQuestionId(Long questionId) {
        QUESTION_ID = questionId;
    }

    private static void setExamId(Long examId) {
        EXAM_ID = examId;
    }

    private static void setQuestionnaireId(Long questionnaireId) {
        QUESTIONNAIRE_ID = questionnaireId;
    }

    private static void setQuestionnaireLogId(Long questionnaireLogId) {
        QUESTIONNAIRE_LOG_ID = questionnaireLogId;
    }

    public static void initIDs(Session session) {
        setAnswerId(new AnswerDao(session).getMaxID() + 1);
        setExamId(new ExamDao(session).getMaxID() + 1);
        setQuestionnaireId(new QuestionnaireDao(session).getMaxID() + 1);
        setQuestionId(new QuestionDao(session).getMaxID() + 1);
        setQuestionnaireLogId(new QuestionnaireLogDao(session).getMaxID() + 1);
    }
}
