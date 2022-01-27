package danielgarashi.DG_SystemManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import danielgarashi.DG_SystemManagement.response_data_entity.DataBaseObject;
import danielgarashi.DG_SystemManagement.service.AuthenticationService;
import danielgarashi.DG_SystemManagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/study-action")
@AllArgsConstructor
public class StudentController {
    private final AuthenticationService authenticationService;
    private final StudentService studentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCourse(@RequestBody Course course){
        try {
            DataBaseObject dataObject = studentService.saveCourse(course);
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "addCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCourse(@RequestBody Student student, @RequestBody Course course){
        try {
            DataBaseObject dataObject = studentService.addStudentCourse(student, course);
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(value = "cancelCourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelCourse(@RequestBody StudentCourse StudentCourse){
        return null;
    }

}
