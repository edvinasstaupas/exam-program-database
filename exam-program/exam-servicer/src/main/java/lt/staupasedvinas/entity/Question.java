package lt.staupasedvinas.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(name = "answer_a", nullable = false)
    private String answerA;

    @Column(name = "answer_b", nullable = false)
    private String answerB;

    @Column(name = "answer_c", nullable = false)
    private String answerC;

    @Column(name = "answer_letter", nullable = false)
    private char rightAnswer;

    @ManyToMany
    @JoinTable(name = "question_questionnaire",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "questionnaire_id"))
    private List<Questionnaire> questionnaireSet = new ArrayList<>();

}
