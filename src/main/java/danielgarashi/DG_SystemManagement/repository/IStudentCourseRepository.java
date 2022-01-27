package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IStudentCourseRepository
        extends MongoRepository<StudentCourse, Integer> {
}
