package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IStudentRepository
        extends MongoRepository<Student, Long> {
}
