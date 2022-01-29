package danielgarashi.DG_SystemManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class StudentCourse {
    public final static int DEFAULT_GRADE = -1;

    @Transient
    public static final String SEQ_NAME = "studentCourse_sequence";

    @Id
    private Long studentCourse_id;
    private Long studentCourse_studentId;
    private Long studentCourse_courseId;
    private Integer  studentCourse_grade;
    private String  studentCourse_registrationDate;
}
