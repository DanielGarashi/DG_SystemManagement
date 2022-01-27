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
public class Course {
    @Id
    private Long course_id;
    private Integer course_year;
    private String course_name;
    private String course_startDate;
    private String course_endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return this.hashCode() == course.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id);
    }
}
