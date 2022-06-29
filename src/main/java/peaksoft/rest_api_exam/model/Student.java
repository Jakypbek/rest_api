package peaksoft.rest_api_exam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import peaksoft.rest_api_exam.model.enam.StudyFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter@Setter
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    private StudyFormat studyFormat;
    @CreatedDate
    private LocalDate created;
    private boolean enabled;



    @ManyToOne
    private Group group;
}
