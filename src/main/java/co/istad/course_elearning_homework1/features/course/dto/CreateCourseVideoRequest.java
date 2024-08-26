package co.istad.course_elearning_homework1.features.course.dto;

public record CreateCourseVideoRequest(
        Integer sectionOrderNo,
        String lectureNo,
        Integer orderNo,
        String title,
        String filename
) {
}
