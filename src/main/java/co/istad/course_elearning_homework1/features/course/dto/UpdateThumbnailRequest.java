package co.istad.course_elearning_homework1.features.course.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateThumbnailRequest(
        @NotBlank(message = "Thumbnail is request!")
        String thumbnail
) {
}
