package lt.staupasedvinas.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Statistic {
    private final String name;

    private final int answers;

    private final int rightAnswerSum;

    private final double averageRightAnswer;

    private final int sumOfA;

    private final int sumOfB;

    private final int sumOfC;
}
