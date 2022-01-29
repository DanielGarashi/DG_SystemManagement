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
    private String msg;

    public DStudent(Student student, Integer studentCoursesAmount, List<StudentCourse> studentCourses, List<Course> optionalCourses) {
        this.student = student;
        this.studentCoursesAmount = studentCoursesAmount;
        this.studentCourses = studentCourses;
        this.optionalCourses = optionalCourses;
        this.msg = null;
    }

    public DStudent(Student student, String msg){
        this.student = student;
        this.studentCoursesAmount = null;
        this.studentCourses= null;
        this.optionalCourses = null;
        this.msg = msg;
    }
}
