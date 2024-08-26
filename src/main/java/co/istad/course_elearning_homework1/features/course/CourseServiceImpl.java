package co.istad.course_elearning_homework1.features.course;

import co.istad.course_elearning_homework1.domain.Category;
import co.istad.course_elearning_homework1.domain.Course;
import co.istad.course_elearning_homework1.domain.Section;
import co.istad.course_elearning_homework1.features.category.CategoryRepository;
import co.istad.course_elearning_homework1.features.course.dto.*;
import co.istad.course_elearning_homework1.mapper.CourseMapper;
import co.istad.course_elearning_homework1.mapper.SectionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final SectionMapper sectionMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CourseSnippetResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        List<Course> courses = courseRepository.findAll();
        List<CourseSnippetResponse> courseSnippetResponses = courses
                .stream()
                .map(courseMapper::courseToCourseSnippetResponse)
                .toList();

        return new PageImpl<>(courseSnippetResponses, pageable, courses.size());
    }

    @Override
    public Page<CourseSnippetResponse> findAllByInstructorName(String instructorName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Course> courses = courseRepository.findAll();
        List<CourseSnippetResponse> courseSnippetResponses = courses
                .stream()
                .filter(course -> course.getInstructorUsername().equals(instructorName))
                .map(courseMapper::courseToCourseSnippetResponse)
                .toList();

        return new PageImpl<>(courseSnippetResponses, pageable, courses.size());
    }

    @Override
    public Page<CourseSnippetResponse> findAllPrivate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Course> courses = courseRepository.findAll();
        List<CourseSnippetResponse> courseSnippetResponses = courses
                .stream()
                .filter(course -> course.getIsDrafted().equals(true))
                .map(courseMapper::courseToCourseSnippetResponse)
                .toList();

        return new PageImpl<>(courseSnippetResponses, pageable, courses.size());
    }

    @Override
    public Page<CourseSnippetResponse> findAllPublic(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Course> courses = courseRepository.findAll();
        List<CourseSnippetResponse> courseSnippetResponses = courses
                .stream()
                .filter(course -> course.getIsDrafted().equals(false))
                .map(courseMapper::courseToCourseSnippetResponse)
                .toList();

        return new PageImpl<>(courseSnippetResponses, pageable, courses.size());
    }

    @Override
    public void createCourse(CreateCourseRequest request) {

        if(courseRepository.existsByTitle(request.title())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course already exists"
            );
        }

        if(!Objects.equals(request.title(), request.slug())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title and Slug do not match"
            );
        }

        Optional<Category> category = categoryRepository.findByName(request.categoryName());
        if(category.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category not found"
            );
        }

        Course course = courseMapper.courseRequestToCourse(request);
        course.setUuid(UUID.randomUUID().toString());
        course.setDiscount(0);
        course.setCode("prgchiso!@#$%");
        course.setCategory(category.get());
        course.setInstructorUsername("Proeung Chiso");
        course.setIsPaid(false);
        course.setIsDrafted(true);
        course.setIsDeleted(false);
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        course.setSections(new ArrayList<>());
        courseRepository.save(course);
    }

    @Override
    public void createCourseVideoByCourseId(String id, CreateCourseVideoRequest request) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
        );
    }

    @Override
    public CourseSnippetResponse findById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
        );

        return courseMapper.courseToCourseSnippetResponse(course);
    }

    @Override
    public void updateCourseById(String id, UpdateCourseRequest request) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );

        Optional<Category> category = categoryRepository.findByName(request.categoryName());
        if(category.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category not found"
            );
        }

        course.setTitle(request.title());
        course.setSlug(request.slug());
        course.setDescription(request.description());
        course.setPrice(request.price());
        course.setDiscount(request.discount());
        course.setCategory(category.get());
        course.setIsPaid(request.isPaid());
        course.setIsDrafted(request.isDrafted());

        courseRepository.save(course);
    }

    @Override
    public void updateThumbnailCourseById(String id, UpdateThumbnailRequest request) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );

        course.setThumbnail(request.thumbnail());
        courseRepository.save(course);
    }

    @Override
    public void updateIsPaidCourseById(String id, UpdateIsPaidRequest request) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );
        course.setIsPaid(request.status());
        courseRepository.save(course);
    }

    @Override
    public void updateVisibilityById(String id, UpdateVisibilityRequest request) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );
        course.setIsDrafted(request.status());
        courseRepository.save(course);
    }

    @Override
    public void enableCourseById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );
    }

    @Override
    public void disableCourseById(String id) {

    }

    @Override
    public void deleteCourseById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course id is required"
            );
        }

        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
        );

        courseRepository.delete(course);
    }

    @Override
    public void updateCourseVideoByCourseId(String courseId, CreateCourseVideoRequest request) {
        
    }

    @Override
    public void createCourseSectionByCourseId(String courseId, CreateCourseSectionRequest request) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found"
                )
        );

        Section section = sectionMapper.sectionToCreateCourseSectionRequest(request);

        if(course.getSections() == null){
            course.setSections(new ArrayList<>());
        }

        course.getSections().add(section);
        courseRepository.save(course);
    }

    @Override
    public CourseSnippetResponse findBySlug(String slug) {
        Course course = courseRepository.findBySlug(slug).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
        );

        return courseMapper.courseToCourseSnippetResponse(course);
    }

}
