package danielgarashi.DG_SystemManagement.service;

import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.repository.CourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentCourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentRepository;
import danielgarashi.DG_SystemManagement.response_data_entity.DCourse;
import danielgarashi.DG_SystemManagement.response_data_entity.DataBaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Data
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;

    public DataBaseObject saveCourse(Course course) {
        List<Course> courseList = courseRepository.saveCourse(course);
        DCourse dCourse = new DCourse(courseList);
        return dCourse;
    }

    public DataBaseObject addCourse(Student student, Course course) {
        DataBaseObject dataObject = null;
        if(!canAddCourse(student, course, dataObject)) {
            return dataObject;
        }

        return dataObject;
    }

    private boolean canAddCourse(Student student, Course course, DataBaseObject dataObject){
        return isStudentAlreadyRegistered(student, dataObject)
            && canRegister(course, dataObject);
    }

    private boolean canRegister(Course course, DataBaseObject dataObject) {
        return true;
    }

    private boolean isStudentAlreadyRegistered(Student student, DataBaseObject dataObject) {
        return true;
    }
}
