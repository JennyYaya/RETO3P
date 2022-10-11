package Service;

import Model.Category;
import Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {

        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> categoryEncontrada = getCategory(category.getId());
            if (categoryEncontrada.isPresent()) {
                return category;
            } else {
                return categoryRepository.save(category);
            }

        }
    }

    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if (categoryEncontrado.isPresent()) {
                if (category.getName() != null) {
                    categoryEncontrado.get().setName(category.getName());
                }
                if (category.getDescription() != null) {
                    categoryEncontrado.get().setDescription(category.getDescription());
                }
                if (category.getQuadbikes() != null) {
                    categoryEncontrado.get().setQuadbikes(category.getQuadbikes());
                }
                categoryRepository.save(categoryEncontrado.get());
                return categoryEncontrado.get();
            } else {
                return category;
            }
        } else {
            return category;
        }
    }
    public boolean delete(int id){
        boolean respuesta = false;
        Optional<Category> categoryEncontrado = categoryRepository.getCategory(id);
        if(categoryEncontrado.isPresent()) {
            categoryRepository.delete(categoryEncontrado.get());
        }
        return respuesta;

    }
}
