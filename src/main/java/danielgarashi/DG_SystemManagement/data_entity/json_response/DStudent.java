package danielgarashi.DG_SystemManagement.data_entity.json_response;

import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DStudent extends DataBaseObject{
    private Student student;
    private Integer studentCoursesAmount;
    private List<StudentCourse> studentCourses;
    private List<Course> optionalCourses;
}
