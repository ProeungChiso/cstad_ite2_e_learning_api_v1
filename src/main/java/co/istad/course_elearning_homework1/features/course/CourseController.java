package co.istad.course_elearning_homework1.features.course;

import co.istad.course_elearning_homework1.features.course.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public Page<CourseSnippetResponse> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return courseService.findAll(page, size);
    }

    @GetMapping("/private")
    public Page<CourseSnippetResponse> getAllCoursesPrivate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return courseService.findAllPrivate(page, size);
    }

    @GetMapping("/public")
    public Page<CourseSnippetResponse> getAllCoursesPublic(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return courseService.findAllPublic(page, size);
    }

    @GetMapping("/instructor/{instructorName}")
    public Page<CourseSnippetResponse> getAllCoursesByInstructor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String instructorName
    ){
        return courseService.findAllByInstructorName(instructorName, page, size);
    }

    @GetMapping("/{id}")
    public CourseSnippetResponse getCourseById(@PathVariable String id){
        return courseService.findById(id);
    }

    @GetMapping("/slug/{slug}")
    public CourseSnippetResponse getCourseBySlug(@PathVariable String slug){
        return courseService.findBySlug(slug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody CreateCourseRequest request){
        courseService.createCourse(request);
    }

    @PostMapping("/{courseId}/videos")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseVideo(@PathVariable String courseId, @RequestBody CreateCourseVideoRequest request){
        courseService.createCourseVideoByCourseId(courseId, request);
    }

    @PostMapping("/{courseId}/section")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseSection(@PathVariable String courseId, @RequestBody CreateCourseSectionRequest request){
        courseService.createCourseSectionByCourseId(courseId, request);
    }

    @PutMapping("/{courseId}/videos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourseSection(@PathVariable String courseId, @RequestBody CreateCourseVideoRequest request){
        courseService.updateCourseVideoByCourseId(courseId, request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourseById(@PathVariable String id, @RequestBody UpdateCourseRequest request){
        courseService.updateCourseById(id, request);
    }

    @PutMapping("/{id}/thumbnail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateThumbnailCourseById(@PathVariable String id, @RequestBody UpdateThumbnailRequest request){
        courseService.updateThumbnailCourseById(id, request);
    }

    @PutMapping("/{id}/is-paid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateIsPaidCourseById(@PathVariable String id, @RequestBody UpdateIsPaidRequest request){
        courseService.updateIsPaidCourseById(id, request);
    }

    @PutMapping("/{id}/visibilities")
    public void updateVisibilityCourseById(@PathVariable String id, @RequestBody UpdateVisibilityRequest request){
        courseService.updateVisibilityById(id, request);
    }

    @PutMapping("/{id}/enable")
    public void enableCourseById(@PathVariable String id){
        courseService.enableCourseById(id);
    }

    @PutMapping("/{id}/disable")
    public void disableCourseById(@PathVariable String id){
        courseService.disableCourseById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable String id){
        courseService.deleteCourseById(id);
    }
}
