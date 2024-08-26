package co.istad.course_elearning_homework1.features.course.dto;

import co.istad.course_elearning_homework1.domain.Section;
import co.istad.course_elearning_homework1.features.category.dto.CategoryResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CourseSnippetResponse(
        String id,
        String uuid,
        String title,
        String slug,
        String description,
        String thumbnail,
        String contents,
        BigDecimal price,
        Integer discount,
        CategoryResponse category,
        List<Section> section,
        String instructorUsername,
        LocalDateTime updatedAt
) {
}
