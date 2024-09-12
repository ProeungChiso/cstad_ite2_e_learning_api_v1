package co.istad.course_elearning_homework1.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

public record BasedStatus(
        @NotNull
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Boolean status
) {
}
