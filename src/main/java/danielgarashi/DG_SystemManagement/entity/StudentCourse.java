package danielgarashi.DG_SystemManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
@ToString(includeFieldNames=true)
public class StudentCourse {
    @Id
    private Integer studentCourse_id;
    private Long studentCourse_studentId;
    private Long studentCourse_courseId;
    private Integer  studentCourse_grade;
    private String  studentCourse_registrationDate;

    public StudentCourse(Long studentCourse_studentId,
                         Long studentCourse_courseId,
                         Integer studentCourse_grade,
                         String studentCourse_registrationDate) {
        this.studentCourse_studentId = studentCourse_studentId;
        this.studentCourse_courseId = studentCourse_courseId;
        this.studentCourse_grade = studentCourse_grade;
        this.studentCourse_registrationDate = studentCourse_registrationDate;
    }
}
