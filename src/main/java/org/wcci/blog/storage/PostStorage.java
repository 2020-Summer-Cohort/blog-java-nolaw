package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Post;
import org.wcci.blog.storage.repositories.PostRepository;

@Service
public class PostStorage {

    PostRepository postRepo;

    public PostStorage(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public Iterable<Post> findAllPosts() {
        return postRepo.findAll();
    }

    public Post findByTitle(String title) {
        return postRepo.findByTitle(title);
    }

    public void savePost(Post post) { postRepo.save(post); }
}
