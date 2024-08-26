package co.istad.course_elearning_homework1.features.course.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateCourseRequest(
        @NotBlank(message = "Title is request")
        String title,
        String slug,
        @Size(max = 500, message = "Description must be less than 500 characters")
        String description,
        String thumbnail,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be a positive value")
        BigDecimal price,
        String contents,
        @NotBlank(message = "Category name is required")
        String categoryName
) {
}
