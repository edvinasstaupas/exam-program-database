package lt.staupasedvinas.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Builder
@Getter
public class PersonalStatistics {
    private final String name;

    private final Long sumOfA;

    private final Long sumOfB;

    private final Long sumOfC;

    private final Long sumOfExams;

    private final Long rightAnswerSum;

    private final double averageRightAnswer;

    private final Map<Long, Long> questionnaireRightAnswerSum;

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder().append(
                "Name: '" + name + '\'' +
                "\non average there were " + averageRightAnswer + " right answers in one exam" +
                "\nsum of A's: " + sumOfA +
                "\nsum of B's: " + sumOfB +
                "\nsum of C's: " + sumOfC +
                "\nsum of exams: " + sumOfExams +
                "\nsum of right answers: " + rightAnswerSum);
        for (Long l: questionnaireRightAnswerSum.keySet()) {
            info.append("\nquestionnaire log id: " + l + " questionnaire right answer sum: " + questionnaireRightAnswerSum.get(l));
        }
        return info.toString();
    }
}
