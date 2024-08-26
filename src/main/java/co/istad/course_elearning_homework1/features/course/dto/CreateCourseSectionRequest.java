package co.istad.course_elearning_homework1.features.course.dto;

import java.util.List;

public record CreateCourseSectionRequest(
        String title,
        Integer orderNo,
        List<CreateCourseVideoRequest> videos
) {
}
