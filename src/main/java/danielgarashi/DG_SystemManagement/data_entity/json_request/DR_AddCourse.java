package danielgarashi.DG_SystemManagement.data_entity.json_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DR_AddCourse {
    private Long student_id;
    private Long course_id;
}
