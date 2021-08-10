package lt.staupasedvinas.pojo.statistics;

import lombok.Builder;
import lombok.Getter;
import lt.staupasedvinas.entity.QuestionnaireLog;

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

    private final Map<QuestionnaireLog, Long> questionnaireRightAnswerSum;

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder().append(
                "Name: '" + name + '\'' +
                        "\nsum of A's: " + sumOfA +
                        "\nsum of B's: " + sumOfB +
                        "\nsum of C's: " + sumOfC +
                        "\nsum of exams: " + sumOfExams +
                        "\nsum of right answers: " + rightAnswerSum +
                        "\non average there were " + averageRightAnswer + " right answers in one exam");
        for (QuestionnaireLog questionnaireLog : questionnaireRightAnswerSum.keySet())
            info.append("\nquestionnaire log id: " + questionnaireLog.getId() + ", questionnaire: " + questionnaireLog.getQuestionnaire().getId() + ", questionnaire right answer sum: " + questionnaireRightAnswerSum.get(questionnaireLog));
        return info.toString();
    }
}
