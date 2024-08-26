package co.istad.course_elearning_homework1.features.course.dto;

import java.math.BigDecimal;

public record UpdateCourseRequest(
        String title,
        String slug,
        String description,
        BigDecimal price,
        Integer discount,
        String categoryName,
        Boolean isPaid,
        Boolean isDrafted
) {
}
