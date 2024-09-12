package co.istad.course_elearning_homework1.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("categories")
public class Category {
    @Id
    private String id;
    private String uuid;
    private String name;
    private String icon;
    private Boolean status;
}
