package co.istad.course_elearning_homework1.features.category;

import co.istad.course_elearning_homework1.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    List<Category> findAllByStatus();
}
