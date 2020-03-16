package adrianromanski.fruits.mapper;

import adrianromanski.domain.Category;
import adrianromanski.mapper.CategoryMapper;
import adrianromanski.model.CategoryDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {
    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}

