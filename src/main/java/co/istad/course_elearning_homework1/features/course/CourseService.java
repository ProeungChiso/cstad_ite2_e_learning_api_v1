package co.istad.course_elearning_homework1.features.course;

import co.istad.course_elearning_homework1.features.course.dto.*;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseSnippetResponse> findAll(int page, int size);
    Page<CourseSnippetResponse> findAllByInstructorName(String instructorName, int page, int size);
    Page<CourseSnippetResponse> findAllPrivate(int page, int size);
    Page<CourseSnippetResponse> findAllPublic(int page, int size);
    CourseSnippetResponse findBySlug(String slug);
    CourseSnippetResponse findById(String id);
    void createCourse(CreateCourseRequest request);
    void createCourseVideoByCourseId(String id, CreateCourseVideoRequest request);
    void updateCourseById(String id, UpdateCourseRequest request);
    void updateThumbnailCourseById(String id, UpdateThumbnailRequest request);
    void updateIsPaidCourseById(String id, UpdateIsPaidRequest request);
    void updateVisibilityById(String id, UpdateVisibilityRequest request);
    void enableCourseById(String id);
    void disableCourseById(String id);
    void deleteCourseById(String id);
    void updateCourseVideoByCourseId(String courseId, CreateCourseVideoRequest request);
    void createCourseSectionByCourseId(String courseId, CreateCourseSectionRequest request);
}
