package lt.staupasedvinas.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "questionnaireSet")
    private List<Question> questionSet = new ArrayList<>();

    @Column(nullable = false)
    private String type;
}
