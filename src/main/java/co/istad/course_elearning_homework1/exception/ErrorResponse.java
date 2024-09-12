package co.istad.course_elearning_homework1.exception;

public record ErrorResponse<T>(T error) {
}
