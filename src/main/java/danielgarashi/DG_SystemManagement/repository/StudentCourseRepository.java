package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<StudentCourse> saveStudentCourse(StudentCourse studentCourse) {
        mongoTemplate.save(studentCourse);
        return getStudentCourses(studentCourse.getStudentCourse_studentId());
    }

    public List<StudentCourse> removeStudentCourse(StudentCourse studentCourse) {
        mongoTemplate.remove(studentCourse);
        return getStudentCourses(studentCourse.getStudentCourse_studentId());
    }

    public List<StudentCourse> getStudentCourses(Long id) {
        Query query = new Query(Criteria.where("studentCourse_studentId").is(id));
        return mongoTemplate.find(query, StudentCourse.class);
    }
}
