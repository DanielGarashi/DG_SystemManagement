package danielgarashi.DG_SystemManagement.service;

import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import danielgarashi.DG_SystemManagement.repository.CourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentCourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentRepository;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DCourse;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DStudent;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DStudentError;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DataBaseObject;
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

    private final static int DEFAULT_GRADE = -1;

    public DataBaseObject saveCourse(Course course) {
        List<Course> courseList = courseRepository.saveCourse(course);
        DCourse dCourse = new DCourse(courseList);
        return dCourse;
    }

    public DataBaseObject addStudentCourse(Student student, Course course) {
        DataBaseObject dataObject = null;
        if (canAddCourse(student, course, dataObject)) {
            String todayDate = ActionHelperService.getTodayDate();
            StudentCourse studentCourseToAdd = new StudentCourse(student.getStudent_id(),
                                                                 course.getCourse_id(),
                                                                -1,
                                                                todayDate);
            List<StudentCourse> studentCourseList = studentCourseRepository.saveStudentCourse(studentCourseToAdd);
            List<Course> optionalCoursesList = courseRepository.getOptionalCourses(student, course);
            dataObject = new DStudent(student,
                                      studentCourseList != null ? studentCourseList.size() : 0,
                                      studentCourseList,
                                      optionalCoursesList);
        }

        return dataObject;
    }

    private boolean canAddCourse(Student student, Course course, DataBaseObject dataObject){
        return isStudentAlreadyRegistered(student, course, dataObject)
            && canRegister(course, dataObject);
    }

    private boolean canRegister(Course course, DataBaseObject dataObject) {
        int courseStartDate = ActionHelperService.getDateAsInt(course.getCourse_startDate());
        int todayDate = ActionHelperService.getTodayDateAsInt();

        if(courseStartDate < todayDate)
            return true;

        dataObject = new DStudentError("The course already started, you can't be registered!");
        return false;
    }

    private boolean isStudentAlreadyRegistered(Student student, Course course, DataBaseObject dataObject) {
        Long studentId = student.getStudent_id();
        Long courseId = course.getCourse_id();
        List<StudentCourse> studentCourse = studentCourseRepository.getStudentCourseByIds(studentId, courseId);

        if(studentCourse.isEmpty())
            return true;

        dataObject = new DStudentError("You already registered to this course!");
        return false;
    }
}
