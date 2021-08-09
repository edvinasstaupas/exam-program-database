package lt.staupasedvinas.service.statistics_service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.dao.AnswerDao;
import lt.staupasedvinas.dao.ExamDao;
import lt.staupasedvinas.dao.QuestionnaireLogDao;
import lt.staupasedvinas.entity.Answer;
import lt.staupasedvinas.entity.Exam;
import lt.staupasedvinas.pojo.PersonalStatistics;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class StatisticsCreatingService {
    public final Session session;
    public final String name;

    public PersonalStatistics init() {
        QuestionnaireLogDao questionnaireLogDao = new QuestionnaireLogDao(session);
        ExamDao examDao = new ExamDao(session);
        AnswerDao answerDao = new AnswerDao(session);
        List<Exam> examList = examDao.loadAllWithName(name);
        List<Answer> answerList = answerDao.loadAllWithName(name);
        //examList.forEach(System.out::println);
        //answerList.forEach(System.out::println);

        Long sumOfA = 0L;
        Long sumOfB = 0L;
        Long sumOfC = 0L;
        Long sumOfRight = 0L;
        Map<Long, Long> map = new HashMap<>();
        for (Answer answer : answerList) {
            switch (answer.getChosenLetter()) {
                case 'a' -> sumOfA++;
                case 'b' -> sumOfB++;
                case 'c' -> sumOfC++;
            }
            Long questionnaireLogId = answer.getQuestionnaireLog().getId();
            if (!map.containsKey(questionnaireLogId))
                if (answer.isRight()) {
                    map.put(questionnaireLogId, 1L);
                    sumOfRight++;
                } else
                    map.put(questionnaireLogId, 0L);
            else {
                Long sum = map.get(questionnaireLogId);
                if (answer.isRight()) {
                    map.replace(questionnaireLogId, sum + 1);
                    sumOfRight++;
                }
            }
            //answerList.stream().filter(Answer::isRight).count()
        }

        return PersonalStatistics.builder()
                .name(name)
                .sumOfExams((long) examList.size())
                .questionnaireRightAnswerSum(map)
                .rightAnswerSum(sumOfRight)
                .questionnaireRightAnswerSum(map)
                .averageRightAnswer((double) sumOfRight / (long) examList.size())
                .sumOfA(sumOfA)
                .sumOfB(sumOfB)
                .sumOfC(sumOfC)
                .build();
    }
}
