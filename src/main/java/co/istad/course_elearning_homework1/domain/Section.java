package co.istad.course_elearning_homework1.domain;

import lombok.*;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    private String title;
    private Integer orderNo;
    private List<Video> videos;
}
