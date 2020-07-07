package org.wcci.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class PostController {

    @Autowired
    PostStorage postStorage;
    @Autowired
    CategoryStorage categoryStorage;
    @Autowired
    TagStorage tagStorage;
    @Autowired
    AuthorStorage authorStorage;

    @GetMapping("posts/{title}")
    public String showSinglePost(@PathVariable String title, Model model) {
        model.addAttribute("post", postStorage.findByTitle(title));
        return "post-template";
    }

    @PostMapping("post/add")
    public String addNewPost(String title, String body, String authors, String tags, String categoryName) {
        Category postCategory = categoryStorage.findByName(categoryName);
        Collection<Tag> postTags = new ArrayList<Tag>();
        postTags.add(tagStorage.findByName(tags));
        Collection<Author> postAuthors = new ArrayList<Author>();
        postAuthors.add(authorStorage.findByName(authors));
        Post postToAdd = new Post(title, body, postAuthors, postTags, postCategory);
        postStorage.savePost(postToAdd);
        return "redirect:/categories/" + postCategory.getName();
    }

}


