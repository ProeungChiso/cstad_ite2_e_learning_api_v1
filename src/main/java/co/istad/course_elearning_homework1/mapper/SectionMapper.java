package co.istad.course_elearning_homework1.mapper;

import co.istad.course_elearning_homework1.domain.Section;
import co.istad.course_elearning_homework1.features.course.dto.CreateCourseSectionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    Section sectionToCreateCourseSectionRequest(CreateCourseSectionRequest createCourseSectionRequest);
}
