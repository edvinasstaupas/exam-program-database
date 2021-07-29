package lt.staupasedvinas.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "questionnaire_log")
public class QuestionnaireLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id", nullable = false)
    private Questionnaire questionnaire;
}
