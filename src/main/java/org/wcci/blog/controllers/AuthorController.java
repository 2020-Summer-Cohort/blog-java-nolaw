package org.wcci.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.AuthorStorage;

@Controller
public class AuthorController {

    @Autowired
    AuthorStorage authorStorage;

    @GetMapping({"authors", ""})
    public String showAllAuthors(Model model) {
        model.addAttribute("authorName", authorStorage.findAllAuthors());
        return "allauthors-template";
    }

    @GetMapping("authors/{authorId}")
    public String showAuthorSpecificPosts(@PathVariable Long authorId, Model model) {
        model.addAttribute("author", authorStorage.findById(authorId));
        return "author-template";
    }

    @PostMapping("authors/add-new")
    public String addNewAuthor(String name) {
        Author newAuthor = new Author(name);
        authorStorage.save(newAuthor);
        return "redirect:/authors/";
    }
}
