package danielgarashi.DG_SystemManagement.data_entity.json_response;

import danielgarashi.DG_SystemManagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DCourse extends DataBaseObject{
    List<Course> courseList;
}
