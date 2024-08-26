package co.istad.course_elearning_homework1.features.category;

import co.istad.course_elearning_homework1.features.category.dto.CategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.CreateCategoryRequest;
import co.istad.course_elearning_homework1.features.category.dto.PopularCategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.UpdateCategoryRequest;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    List<PopularCategoryResponse> findAllPopular();
    CategoryResponse findById(String id);
    void createCategory(CreateCategoryRequest request);
    void updateCategoryById(UpdateCategoryRequest request, String id);
    void deleteCategoryById(String id);
    void disableCategoryById(String id);
    void enableCategoryById(String id);
}
