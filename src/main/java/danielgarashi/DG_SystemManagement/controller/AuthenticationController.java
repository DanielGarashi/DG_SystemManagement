package danielgarashi.DG_SystemManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danielgarashi.DG_SystemManagement.data_entity.json_request.DR_SignUp;
import danielgarashi.DG_SystemManagement.entity.Student;
import danielgarashi.DG_SystemManagement.data_entity.json_response.DataBaseObject;
import danielgarashi.DG_SystemManagement.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authentication-action")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO: STRINGS
    private static String SERVER_GENERAL_ERROR_ENG = "Unknown error, please try again.";
    private static String SERVER_GENERAL_ERROR_HEB = "קרתה תקלה לא ידועה, אנא נסה שוב.";

    @RequestMapping(value = "signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUp(@RequestBody DR_SignUp dr_signUp){
        try {
            DataBaseObject dataObject = authenticationService.signUp(dr_signUp.getStudent());
            String jsonDataObject = objectMapper.writeValueAsString(dataObject);
            if (dataObject.isErrorClass())
                return ResponseEntity.badRequest().body(jsonDataObject);

            return ResponseEntity.ok().body(jsonDataObject);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
        }
    }

   @RequestMapping(value = "signIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signIn(@RequestParam Long student_id, @RequestParam String student_password) {
       try {
           DataBaseObject dataObject = authenticationService.signIn(student_id, student_password);
           String jsonDataObject = objectMapper.writeValueAsString(dataObject);
           if (dataObject.isErrorClass())
               return ResponseEntity.badRequest().body(jsonDataObject);

           return ResponseEntity.ok().body(jsonDataObject);
       } catch (JsonProcessingException e) {
           return ResponseEntity.internalServerError().body(SERVER_GENERAL_ERROR_HEB);
       }
   }
}
