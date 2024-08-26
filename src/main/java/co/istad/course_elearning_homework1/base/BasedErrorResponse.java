package co.istad.course_elearning_homework1.base;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasedErrorResponse {
    private BasedError error;
}
