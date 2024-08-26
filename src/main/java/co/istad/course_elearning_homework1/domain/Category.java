package co.istad.course_elearning_homework1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document("categories")
public class Category {
    @Id
    private String id;
    private String uuid;
    private String name;
    private String icon;
    private Boolean status;
}
