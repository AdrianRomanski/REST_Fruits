package adrianromanski.controllers;

import adrianromanski.model.CategoryDTO;
import adrianromanski.model.CategoryListDTO;
import adrianromanski.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        return new ResponseEntity<>(new CategoryListDTO
                (categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(
                categoryService.getCategoriesByName(name), HttpStatus.OK);
    }
}
