package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

@Service
public class CategoryStorage {

    CategoryRepository categoryRepo;

    public CategoryStorage(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Category findByName(String name) {
        return categoryRepo.findByName(name);
    }

    public void save(Category category) {
        categoryRepo.save(category);
    }

    public Category findById(long id) {
        return categoryRepo.findById(id).get();
    }
}
