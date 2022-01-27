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
public class CourseRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<Course> getAllCourses()
    {
        return mongoTemplate.findAll(Course.class);
    }

    public List<Course> saveCourse(Course course) {
        mongoTemplate.save(course);
        return getAllCourses();
    }

    public List<Course> removeCourse(Course course) {
        mongoTemplate.remove(course);
        return getAllCourses();
    }

    public List<Course> getStudentOptionalCourses(Long id)
    {
        //Query query = new Query(Criteria.where("course_id"));
        return getAllCourses();
    }
}
