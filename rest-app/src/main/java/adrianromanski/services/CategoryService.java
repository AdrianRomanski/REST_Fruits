package adrianromanski.services;

import adrianromanski.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoriesByName(String name);
}
