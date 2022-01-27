package danielgarashi.DG_SystemManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document()
@AllArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class Student {
    @Id
    private Long student_id;
    private String student_password;
    private String student_firstName;
    private String student_lastName;
    private String student_email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return this.hashCode() == student.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id);
    }
}
