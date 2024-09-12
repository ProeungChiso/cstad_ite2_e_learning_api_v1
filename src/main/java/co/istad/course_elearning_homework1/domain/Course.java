package co.istad.course_elearning_homework1.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Document("courses")
public class Course {
    @Id
    private String id;

    private String uuid;
    private String title;
    private String slug;
    private String description;
    private String thumbnail;
    private String contents;
    private BigDecimal price;
    private Integer discount;
    private List<Section> sections;
    private String categoryName;

    private String code;
    private String instructorUsername;

    private Boolean isPaid;
    private Boolean isDrafted;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
