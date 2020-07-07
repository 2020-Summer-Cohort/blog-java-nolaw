package org.wcci.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;


@Controller
public class CategoryController {

    @Autowired
    CategoryStorage categoryStorage;
    @Autowired
    AuthorStorage authorStorage;
    @Autowired
    TagStorage tagStorage;


    @GetMapping("categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categoryNames", categoryStorage.findAllCategories());
        return "category-template";
    }

    @GetMapping("/categories/{categoryName}")
    public String showCategorySpecificPosts(Model model, @PathVariable String categoryName) {
        model.addAttribute("category", categoryStorage.findByName(categoryName));
        model.addAttribute("authors", authorStorage.findAllAuthors());
        model.addAttribute("tags", tagStorage.findAllTags());
        return "subcategory-template";
    }
}
