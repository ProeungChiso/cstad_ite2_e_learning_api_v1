package co.istad.course_elearning_homework1.mapper;

import co.istad.course_elearning_homework1.domain.Course;
import co.istad.course_elearning_homework1.features.course.dto.CourseSnippetResponse;
import co.istad.course_elearning_homework1.features.course.dto.CreateCourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course courseRequestToCourse(CreateCourseRequest createCourseRequest);
    CourseSnippetResponse courseToCourseSnippetResponse(Course course);
}
