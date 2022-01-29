package danielgarashi.DG_SystemManagement.data_entity.json_request;

import danielgarashi.DG_SystemManagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DR_SaveCourse {
    private Course course;
}
