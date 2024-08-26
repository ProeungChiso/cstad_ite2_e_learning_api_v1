package co.istad.course_elearning_homework1.features.category;

import co.istad.course_elearning_homework1.features.category.dto.CategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.CreateCategoryRequest;
import co.istad.course_elearning_homework1.features.category.dto.PopularCategoryResponse;
import co.istad.course_elearning_homework1.features.category.dto.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/popular")
    public List<PopularCategoryResponse> getAllPopularCategories(){
        return categoryService.findAllPopular();
    }

    @GetMapping("/{id}")
    public CategoryResponse findCategoryById(@PathVariable String id){
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@RequestBody CreateCategoryRequest request){
        categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategoryById(@RequestBody UpdateCategoryRequest request, @PathVariable String id){
        categoryService.updateCategoryById(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable String id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}/disabled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableCategoryById(@PathVariable String id){
        categoryService.disableCategoryById(id);
    }

    @PutMapping("/{id}/enabled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableCategoryById(@PathVariable String id){
        categoryService.enableCategoryById(id);
    }
}
