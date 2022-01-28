package danielgarashi.DG_SystemManagement.data_entity.json_response;

import danielgarashi.DG_SystemManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DStudentsList extends DataBaseObject {
    List<Student> studentList;
}
