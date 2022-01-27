package danielgarashi.DG_SystemManagement.response_data_entity;

import danielgarashi.DG_SystemManagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DCourse extends DataBaseObject{
    List<Course> courseList;
}
