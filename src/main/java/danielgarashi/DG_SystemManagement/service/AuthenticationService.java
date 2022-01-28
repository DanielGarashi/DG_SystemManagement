package danielgarashi.DG_SystemManagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import danielgarashi.DG_SystemManagement.repository.CourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentCourseRepository;
import danielgarashi.DG_SystemManagement.repository.StudentRepository;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DErrorValidation;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DStudent;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DStudentsList;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DataBaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Data
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;

    private final Logger AUTHENTICATOR_SERVICE_LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String ENGLISH_HEBREW_AND_DIGITS_REG_EXP = "[a-zA-Z\u0590-\u05ff0-9]+"; // [0-9א-ת]+
    private static final String ENGLISH_AND_HEBREW_REG_EXP = "[a-zA-Z\u0590-\u05ff]+";
    private static final String ENGLISH_AND_DIGITS_REG_EXP = "[a-zA-Z0-9]+";
    private static final String HEBREW_AND_DIGITS_REG_EXP = "[\u0590-\u05ff0-9]+"; // [0-9א-ת]+
    private static final String ENGLISH_REG_EXP = "[a-zA-Z]+";
    private static final String HEBREW_REG_EXP = "[\u0590-\u05ff]+";
    private static final String DIGITS_REG_EXP = "[0-9]+";
    private static final String EMAIL_REG_EXP = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                                                "[a-zA-Z0-9_+&*-]+)*@" +
                                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                                "A-Z]{2,7}$";

    private static final int FIELDS_AMOUNT = 5;
    private static final int ID_FIELD = 0;
    private static final int PASSWORD_FIELD = 1;
    private static final int FIRST_NAME_FIELD = 2;
    private static final int LAST_NAME_FIELD = 3;
    private static final int EMAIL_FIELD = 4;

    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MIN_FULL_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 2;

    public DataBaseObject signUp(Student studentToSave) {
        if(isStudentAlreadyExists(studentToSave.getStudent_id()))
        {
            String errorMsg = String.format("This user already in use, please try again");
            AUTHENTICATOR_SERVICE_LOGGER.error("Error signUp_action: " + errorMsg);
            return DErrorValidation.getError(errorMsg);
        }

        boolean[] invalidateFields = new boolean[FIELDS_AMOUNT];
        boolean isValidParam = isValidateParam(studentToSave.getStudent_id(),
                studentToSave.getStudent_password(),
                studentToSave.getStudent_firstName(),
                studentToSave.getStudent_lastName(),
                studentToSave.getStudent_email(),
                invalidateFields);

        if (!isValidParam) {
            String missingFields = buildMissingFieldsMsg(invalidateFields);
            String errorMsg = String.format("One or more of the parameters you entered are invalid#%s", missingFields);
            AUTHENTICATOR_SERVICE_LOGGER.error(String.format("Error signUp_action: %s", errorMsg));
            return DErrorValidation.getError(errorMsg);
        }

        AUTHENTICATOR_SERVICE_LOGGER.trace(String.format("signUp_action: Student with id %d- signUp succeeded", studentToSave.getStudent_id()));
        List<Student> studentList = studentRepository.saveStudent(studentToSave);
        DStudentsList dStudentsList = new DStudentsList(studentList);
        return dStudentsList;
    }

    private boolean isStudentAlreadyExists(Long id) {
        return !(studentRepository.getStudentById(id) == null);
    }

    private boolean isValidateParam(Long id, String password, String firstName, String lastName, String email, boolean[] validateFields) {
        boolean isValid = true;

        validateFields[ID_FIELD] = isValidId(id);
        validateFields[PASSWORD_FIELD] = isValidPassword(password);
        validateFields[FIRST_NAME_FIELD] = isValidName(firstName, lastName);
        validateFields[LAST_NAME_FIELD] = validateFields[FIRST_NAME_FIELD];
        validateFields[EMAIL_FIELD] = isValidEmail(email);

        for (boolean field : validateFields) {
            isValid = isValid && field;
        }

        return isValid;
    }

    private boolean isValidId(Long id) {
        if (id == null || id < 100000000L || 999999999L < id)
            return false;

        int digitsId = 0;
        boolean isEvenPos = true;

        for (int digitPos = 0; digitPos < 9; digitPos++) {
            long currDig = id % 10;
            if (isEvenPos)
                digitsId += currDig;
            else
                digitsId += currDig * 2 > 9 ? (currDig * 2) / 10 + (currDig * 2) % 10 : currDig * 2;

            isEvenPos = !isEvenPos;
            id /= 10;
        }

        return digitsId % 10 == 0;
    }

    private boolean isValidPassword(String password) {
        return password.length() > MIN_PASSWORD_LENGTH
                &&  password.matches(ENGLISH_HEBREW_AND_DIGITS_REG_EXP)
                && !password.matches(ENGLISH_AND_HEBREW_REG_EXP)
                && !password.matches(DIGITS_REG_EXP);
    }

    private boolean isValidName(String firstname, String lastname) {
        return firstname != null && lastname != null
                && MIN_NAME_LENGTH <= firstname.length() && MIN_NAME_LENGTH <= lastname.length()
                && MIN_FULL_NAME_LENGTH <= (firstname.length() + lastname.length())
                && firstname.matches(HEBREW_REG_EXP)
                && lastname.matches(HEBREW_REG_EXP);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REG_EXP);
    }

    private String buildMissingFieldsMsg(boolean[] validateFields) {
        StringBuilder sb_msg = new StringBuilder();
        if(!validateFields[ID_FIELD])
            sb_msg.append("Invalid Id_");

        if(!validateFields[PASSWORD_FIELD])
            sb_msg.append("Invalid Password_");

        if(!validateFields[FIRST_NAME_FIELD])
            sb_msg.append("Invalid Name_");

       /* if(validateFields[LAST_NAME_FIELD])
            sb_msg.append("last name_");*/

        if(!validateFields[EMAIL_FIELD])
            sb_msg.append("Invalid Email_");

        return sb_msg.toString();
    }

    public DataBaseObject signIn(Long id, String password){
        Student student = studentRepository.getStudentById(id);
        if(student == null || !student.getStudent_password().equals(password)){
            String errorMsg = String.format("One or more of the parameters you entered are invalid");
            AUTHENTICATOR_SERVICE_LOGGER.error("Error signIn_action: {" + id + ", " + password + "}");
            return DErrorValidation.getError(errorMsg);
        }
        List<StudentCourse> studentCoursesList = studentCourseRepository.getStudentCourses(id);
        Integer studentCoursesAmount = studentCoursesList != null ? studentCoursesList.size() : 0;
        List<Course> courseList = courseRepository.getStudentOptionalCourses(id);
        DStudent dStudent = new DStudent(student, studentCoursesAmount, studentCoursesList, courseList);

        AUTHENTICATOR_SERVICE_LOGGER.trace("signIn_action: {" + id + ", " + password + "} succeeded");
        return dStudent;
    }
}
