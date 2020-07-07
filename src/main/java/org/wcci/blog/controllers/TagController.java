package org.wcci.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class TagController {

    @Autowired
    TagStorage tagStorage;
    @Autowired
    PostStorage postStorage;

    @GetMapping("hashtags")
    public String showAllTags(Model model) {
        model.addAttribute("tagNames", tagStorage.findAllTags());
        return "alltags-template";
    }

    @GetMapping("hashtags/{hashtagID}")
    public String showReviewsAssociatedWithHashtag(@PathVariable Long hashtagID, Model model) {
        model.addAttribute("tag", tagStorage.findByID(hashtagID));
        return "tag-template";
    }

    @PostMapping("hashtags/add")
    public String addNewHashtagToReview(String hashtagName, String title) {
        if(tagStorage.isTagExists(hashtagName)) {
            Tag tag = tagStorage.findByName(hashtagName);
            Post post = postStorage.findByTitle(title);
            post.addTag(tag);
            postStorage.savePost(post);
        } else {
            Tag tagToAdd = new Tag(hashtagName);
            tagStorage.saveTag(tagToAdd);
            Post post = postStorage.findByTitle(title);
            post.addTag(tagToAdd);
            postStorage.savePost(post);
        }
        return "redirect:/posts/" + title;
    }

    @PostMapping("hashtags/add-new")
    public String addNewHashtag(String hashtagName) {
        Tag newTag = new Tag(hashtagName);
        tagStorage.saveTag(newTag);
        return "redirect:/hashtags/";
    }
}
