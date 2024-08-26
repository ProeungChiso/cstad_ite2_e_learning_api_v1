package co.istad.course_elearning_homework1.features.category.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopularCategoryResponse {
    String name;
    String icon;
    Long totalCourses;
}
