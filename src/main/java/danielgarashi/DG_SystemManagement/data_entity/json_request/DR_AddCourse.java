package danielgarashi.DG_SystemManagement.data_entity.json_request;

import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DR_AddCourse {
    private Student student;
    private Course course;
}
