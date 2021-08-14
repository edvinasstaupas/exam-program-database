package lt.staupasedvinas.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne()
    @JoinColumn(name = "questionnaire_log_id", referencedColumnName = "id", nullable = false)
    private QuestionnaireLog questionnaireLog;
}
