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

import static danielgarashi.DG_SystemManagement.entity.IndicationMsg.*;

@Service
@AllArgsConstructor
@Data
public class StudentService {
    private final AuthenticationService authenticationService;
    private final SequenceGeneratorService sequenceGeneratorService;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    public DataBaseObject saveCourse(Course course) {
        List<Course> courseList = courseRepository.saveCourse(course);
        DCourse dCourse = new DCourse(courseList);
        return dCourse;
    }

    public DataBaseObject addStudentCourse(Long student_id, Long course_id) {
        DataBaseObject dataObject = new DStudentError();
        if (canAddCourse(student_id, course_id, (DStudentError) dataObject)) {
            Long studentCourse_id =  sequenceGeneratorService.getSequenceNumber(StudentCourse.SEQ_NAME);
            String todayDate = ActionHelperService.getTodayDate();
            StudentCourse studentCourseToAdd = new StudentCourse(studentCourse_id,
                                                                student_id,
                                                                 course_id,
                                                StudentCourse.DEFAULT_GRADE,
                                                                todayDate);
            List<StudentCourse> studentCourseList = studentCourseRepository.saveStudentCourse(studentCourseToAdd);
            Student student = studentRepository.getStudentById(student_id);
            List<Course> optionalCoursesList = getOptionalCourses(student_id);
            dataObject = new DStudent(student,
                                      studentCourseList != null ? studentCourseList.size() : 0,
                                      studentCourseList,
                                      optionalCoursesList);
        }
        return dataObject;
    }

    private boolean canAddCourse(Long student_id, Long course_id, DStudentError dStudentError){
        return isStudentExists(student_id, dStudentError) && isCourseExists(course_id, dStudentError) && isStudentAlreadyRegistered(student_id, course_id, dStudentError)
            && canRegister(course_id, (DStudentError) dStudentError);
    }

    private boolean isStudentExists(Long student_id, DStudentError dStudentError) {
        if(authenticationService.isStudentExists(student_id))
            return true;

        dStudentError.setError(STUDENT_NOT_EXISTS_ERROR_HEB);
        return false;
    }

    private boolean isCourseExists(Long course_id, DStudentError dStudentError) {
        Course course = courseRepository.getCourseByIdD(course_id);
        if(course != null)
            return true;

        dStudentError.setError(COURSE_NOT_EXISTS_ERROR_HEB);
        return false;
    }

    private boolean canRegister(Long course_id, DStudentError dStudentError) {
        Course course = courseRepository.getCourseByIdD(course_id);
        if(!isCourseStarted(course)) {
            return true;
        }

        dStudentError.setError(COURSE_STARTED_ERROR_HEB);
        return false;
    }

    private boolean isCourseStarted(Course course){
        int courseStartDate = ActionHelperService.getDateAsInt(course.getCourse_startDate());
        int todayDate = ActionHelperService.getTodayDateAsInt();

        if(todayDate < courseStartDate)
            return false;

        return true;
    }

    private boolean isStudentAlreadyRegistered(Long studentId, Long course_id, DStudentError dStudentError) {
        List<StudentCourse> studentCourse = studentCourseRepository.getStudentCourseByIds(studentId, course_id);

        if(studentCourse.isEmpty())
            return true;

        dStudentError.setError(STUDENT_REGISTERED_COURSE_HEB);
        return false;
    }

    public DataBaseObject cancelCourse(Long studentId, Long sc_id) {
        DataBaseObject dataObject = new DStudentError();

        if(isCanCancelCourse(studentId, sc_id, (DStudentError) dataObject)){
            List<StudentCourse> studentCourses = studentCourseRepository.removeStudentCourse(sc_id);
            if(isCourseCanceled(sc_id, studentCourses, (DStudentError) dataObject)){
                Student student = studentRepository.getStudentById(studentId);
                List<Course> optionalCourses = getOptionalCourses(studentId);
                dataObject = new DStudent(student,
                        studentCourses != null ? studentCourses.size() : 0,
                        studentCourses,
                        optionalCourses,
                        CANCEL_COURSE_SUCCESS_HEB);
            }
        }
        return dataObject;
    }

    private boolean isCanCancelCourse(Long studentId, Long sc_id, DStudentError dStudentError){
        Course course = courseRepository.getCourseByIdD(sc_id);
        if(!isStudentCourseExists(studentId, sc_id, dStudentError)){
            return false;
        }
        else if(isCourseStarted(course)) {
            dStudentError.setError(STUDENT_CANCEL_STARTED_COURSE_ERROR_HEB);
            return false;
        }

        return true;
    }

    private boolean isStudentCourseExists(Long studentId, Long sc_id, DStudentError dStudentError) {
        StudentCourse studentCourse = studentCourseRepository.getStudentCourseById(sc_id);
        if(studentCourse != null && Long.compare(studentCourse.getStudentCourse_studentId(), studentId) == 0)
            return true;

        dStudentError.setError(STUDENT_COURSE_NOT_EXISTS_ERROR_HEB);
        return false;
    }

    private boolean isCourseCanceled(Long sc_id, List<StudentCourse> studentCourses, DStudentError dStudentError){
        for(StudentCourse sc: studentCourses){
            if(Long.compare(sc_id, sc.getStudentCourse_id()) == 0) {
                dStudentError.setError("התרחשה בעיה בעת ניסיון ביטול הקורס, אנא נסה שוב.");
                return false;
            }
        }
        return true;
    }

    private List<Course> getOptionalCourses(Long student_id) {
        return courseRepository.getOptionalCourses(student_id);
    }

    public DataBaseObject updateStudentDetails(String verificationPassword, Student studentToUpdate) {
        return authenticationService.updateStudentDetails(verificationPassword, studentToUpdate);
    }
}
