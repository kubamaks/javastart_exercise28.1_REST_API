package pl.km.exercise281.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.km.exercise281.exceptions.EntityNotFoundException;
import pl.km.exercise281.exceptions.InvalidDataInputException;
import pl.km.exercise281.offer.Offer;
import pl.km.exercise281.offer.OfferRepository;
import java.util.ArrayList;
import java.util.List;

@Service
class CategoryService {
    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public CategoryService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getCategoryDtoList() {
        List<Category> categories = findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = createCategoryDto(category);
            Long offerCount = offerRepository.findAllByCategory_Id(category.getId()).stream().count();
            categoryDto.setOffers(offerCount);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public boolean isCategoryNamePresent(String categoryName) {
        List<Category> categories = findAll();
        for (Category cat : categories) {
            if (cat.getName().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public CategoryInsertDto saveNew(CategoryInsertDto categoryInsertDto) {
        if (isCategoryNamePresent(categoryInsertDto.getName())) {
            throw new InvalidDataInputException("Category names should be unique");
        }
        Category category = new Category();
        fillFields(category, categoryInsertDto);
        categoryRepository.save(category);
        return categoryInsertDto;
    }

    public List<String> getCategoryNames() {
        return findAll().stream().map(Category::getName).toList();
    }

    public void deleteCategoryById(Long id) {
        List<Offer> offers = offerRepository.findAllByCategory_Id(id);
        if (!offers.isEmpty()) {
            offerRepository.deleteAll(offers);
        }
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("No category with introduced \"id\" found.");
        }
    }

    private List<Category> findAll() {
        return categoryRepository.findAll();
    }

    private CategoryDto createCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    private Category fillFields(Category category, CategoryInsertDto categoryInsertDto) {
        category.setName(categoryInsertDto.getName());
        category.setDescription(categoryInsertDto.getDescription());
        return category;
    }
}
