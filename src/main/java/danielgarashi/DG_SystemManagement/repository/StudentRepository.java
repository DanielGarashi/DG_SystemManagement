package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.Course;
import danielgarashi.DG_SystemManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<Student> getAllStudents()
    {
        return mongoTemplate.findAll(Student.class);
    }

    public List<Student> saveStudent(Student student) {
        mongoTemplate.save(student);
        return getAllStudents();
    }

    public Student getStudentById(Long id) {
        return mongoTemplate.findById(id, Student.class);
    }

    public List<Student> getStudentsByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.find(query, Student.class);
    }

    public Student updateStudent(Student studentToUpdate) {
        return mongoTemplate.save(studentToUpdate);
    }
}
