package lt.staupasedvinas.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne()
    @JoinColumn(name = "questionnaire_log_id",
            referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "questionnaire_log_id_answer_fkey")
    )
    private QuestionnaireLog questionnaireLog;

    @Column(name = "is_right", nullable = false)
    private boolean isRight;

    @Column(name = "chosen_letter", nullable = false)
    private char chosenLetter;
}
