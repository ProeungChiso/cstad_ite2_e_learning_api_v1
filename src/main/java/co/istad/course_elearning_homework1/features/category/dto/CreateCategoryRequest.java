package co.istad.course_elearning_homework1.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "Category name is require!")
        String name,
        String icon
) {
}
