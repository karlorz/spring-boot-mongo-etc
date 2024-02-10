package com.report.service.utils;

import com.report.service.repository.PostRepository;
import com.report.service.documnent.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Component
@Slf4j
public class HelperUtil {

    private final PostRepository postRepository;

    @Autowired
    public HelperUtil(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void insertDemoData() {
        // Check if there is existing data
        if (postRepository.count() == 0) {
            // Insert demo data
            Post post1 = new Post("John Doe", "Full-time",
                    new String[]{"Java", "Spring Boot"}, "100000");
            postRepository.save(post1);

            Post post2 = new Post("Jane Smith", "Part-time",
                    new String[]{"Python", "Django"}, "999");
            postRepository.save(post2);

            // Add more demo data as needed

            log.info("Demo data inserted successfully!");
        } else {
            log.info("Data already exists in the database.");
        }
    }
}
