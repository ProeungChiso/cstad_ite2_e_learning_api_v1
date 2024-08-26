package co.istad.course_elearning_homework1.features.course;

import co.istad.course_elearning_homework1.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    boolean existsByTitle(String name);
    Optional<Course> findBySlug(String slug);
    List<Course> findByCategoryName(String categoryName);
}
