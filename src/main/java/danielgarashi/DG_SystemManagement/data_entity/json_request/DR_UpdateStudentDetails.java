package danielgarashi.DG_SystemManagement.data_entity.json_request;

import danielgarashi.DG_SystemManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DR_UpdateStudentDetails {
    private Student studentToUpdate;
    private String verificationPassword;
}
