package danielgarashi.DG_SystemManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danielgarashi.DG_SystemManagement.data_entity.json_request.DR_AddCourse;
import danielgarashi.DG_SystemManagement.data_entity.json_request.DR_SaveCourse;
import danielgarashi.DG_SystemManagement.data_entity.json_request.DR_UpdateStudentDetails;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DataBaseObject;
import danielgarashi.DG_SystemManagement.service.AuthenticationService;
import danielgarashi.DG_SystemManagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student-action")
@AllArgsConstructor
public class StudentController {
    private final AuthenticationService authenticationService;
    private final StudentService studentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO: STRINGS
    private static String SERVER_GENERAL_ERROR_ENG = "Unknown error, please try again.";
    private static String SERVER_GENERAL_ERROR_HEB = "קרתה תקלה לא ידועה, אנא נסה שוב.";

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCourse(@RequestBody DR_SaveCourse dr_saveCourse){
        try {
            DataBaseObject dataObject = studentService.saveCourse(dr_saveCourse.getCourse());
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
        }
    }

    @RequestMapping(value = "addCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCourse(@RequestBody DR_AddCourse dr_addCourse){
        try {
            DataBaseObject dataObject = studentService.addStudentCourse(dr_addCourse.getStudent_id(), dr_addCourse.getCourse_id());
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
        }
    }

    @RequestMapping(value = "cancelCourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelCourse(@RequestParam Long studentId, @RequestParam Long studentCourseId){
        try {
            DataBaseObject dataObject = studentService.cancelCourse(studentId, studentCourseId);
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
        }
    }

    @RequestMapping(value = "updateStudentDetails", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudentDetails(@RequestBody DR_UpdateStudentDetails dr_updateStudentDetails){
        try {
            DataBaseObject dataObject = studentService.updateStudentDetails(dr_updateStudentDetails.getVerificationPassword(),
                                                                            dr_updateStudentDetails.getStudentToUpdate());
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
        }
    }

}
