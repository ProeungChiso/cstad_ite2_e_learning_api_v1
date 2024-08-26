package co.istad.course_elearning_homework1.mapper;

import co.istad.course_elearning_homework1.domain.Category;
import co.istad.course_elearning_homework1.features.category.dto.CategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.CreateCategoryRequest;
import co.istad.course_elearning_homework1.features.category.dto.PopularCategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse categoryToCategoryResponse(Category category);
    PopularCategoryResponse categoryToPopularCategoryResponse(Category category);
    Category categoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

}
