package pl.km.exercise281.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/categories")
@RestController
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<CategoryDto> getCategories() {
        return categoryService.getCategoryDtoList();
    }

    @GetMapping("/names")
    public List<String> getCategoryNames() {
        return categoryService.getCategoryNames();
    }

    @PostMapping("")
    public ResponseEntity<CategoryInsertDto> saveNewCategory(@RequestBody CategoryInsertDto category) {
        CategoryInsertDto catDto = categoryService.saveNew(category);
        return ResponseEntity.ok(catDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
