package co.istad.course_elearning_homework1.features.category;

import co.istad.course_elearning_homework1.domain.Category;
import co.istad.course_elearning_homework1.domain.Course;
import co.istad.course_elearning_homework1.features.category.dto.CategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.CreateCategoryRequest;
import co.istad.course_elearning_homework1.features.category.dto.PopularCategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.UpdateCategoryRequest;
import co.istad.course_elearning_homework1.features.course.CourseRepository;
import co.istad.course_elearning_homework1.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CourseRepository courseRepository;

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll().stream().filter(Category::getStatus).toList();
        return categories.stream().map(categoryMapper::categoryToCategoryResponse).toList();
    }

    @Override
    public List<PopularCategoryResponse> findAllPopular() {
        List<Category> categories = categoryRepository
                .findAll()
                .stream()
                .filter(Category::getStatus)
                .toList();

        List<PopularCategoryResponse> popularCategories = categories.stream()
                .map(categoryMapper::categoryToPopularCategoryResponse)
                .collect(Collectors.toList());

        popularCategories.forEach(popularCategoryResponse -> {
            List<Course> courses = courseRepository.findByCategoryName(popularCategoryResponse.getName());
            popularCategoryResponse.setTotalCourses((long) courses.size());
        });

        return popularCategories;
    }


    @Override
    public CategoryResponse findById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category id is required");
        }

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category with id " + id + " not found"
                        ));

        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public void createCategory(CreateCategoryRequest request) {

        if(categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category with name " + request.name() + " already exists"
            );
        }

        Category category = categoryMapper.categoryRequestToCategory(request);
        category.setUuid(UUID.randomUUID().toString());
        category.setIcon("http://localhost/images/category/"+request.icon());
        category.setStatus(true);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryById(UpdateCategoryRequest request, String id) {

        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category id is required"
            );
        }

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category with id " + id + " not found"
                        ));

        category.setName(request.name());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(String id) {
        if(id == null || id.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category id is required"
            );
        }
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category with id " + id + " not found"
                        ));
        categoryRepository.delete(category);
    }

    @Override
    public void disableCategoryById(String id) {
        if(id == null || id.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category id is required"
            );
        }

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category id = " + id + " not found"
                )
        );

        category.setStatus(false);
        categoryRepository.save(category);
    }

    @Override
    public void enableCategoryById(String id) {
        if(id == null || id.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category id is required"
            );
        }

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category id = " + id + " not found"
                        )
                );

        category.setStatus(true);
        categoryRepository.save(category);
    }
}
