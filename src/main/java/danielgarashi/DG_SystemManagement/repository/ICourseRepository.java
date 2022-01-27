package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICourseRepository extends MongoRepository<Course, Long> {

}
