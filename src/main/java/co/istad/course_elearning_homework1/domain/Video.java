package co.istad.course_elearning_homework1.domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    private Integer orderNo;
    private String lectureNo;
    private String title;
    private String fileName;
}
