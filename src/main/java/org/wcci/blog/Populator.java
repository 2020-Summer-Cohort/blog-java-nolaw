package org.wcci.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.storage.repositories.TagRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class Populator implements CommandLineRunner {

    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    PostRepository postRepo;
    @Autowired
    TagRepository tagRepo;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("Epics");
        Category category2 = new Category("Happy Happenstance");
        categoryRepo.save(category1);
        categoryRepo.save(category2);

        Author author1 = new Author("Mignonne Silva");
        Author author2 = new Author("Daniel Wilcox");
        Author author3 = new Author("Goodcop and Rey");

        authorRepo.save(author1);
        authorRepo.save(author2);
        authorRepo.save(author3);

        ArrayList<Author> authors = new ArrayList<Author>();
            authors.add(author1);
            authors.add(author2);

       ArrayList<Author> authors2 = new ArrayList<>();
            authors2.add(author2);
            authors2.add(author3);

        Tag tag1 = new Tag("#rollingstone");
        Tag tag2 = new Tag("#adventurebug");
        tagRepo.save(tag1);
        tagRepo.save(tag2);

        Post post1 = new Post("NoLaw", "During New Year's we took a trip to gorgeous New Orleans and while driving around the city exploring the driving route where the levy was breached we found this industrial area and just had to take a picture of the Subie near this sign. It was too perfect.", authors, LocalDateTime.parse("2020-01-15T22:00:00"), category1, tag1, tag2);
        Post post2 = new Post("'Bama Bakery", "While driving back from down South we had to get gas and drove into this small town and en route to a cafe we just happened by an authentic Swedish bakery right in this little old town in Alabama. The best part of traveling is these absolute gems. And a bakery at that!", authors2, LocalDateTime.parse("2020-03-05T11:30:00"), category2, tag1);
        postRepo.save(post1);
        postRepo.save(post2);
    }

}
