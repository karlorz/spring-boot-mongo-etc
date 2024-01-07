package com.report.service.utils;

import com.report.service.dao.PostDao;
import com.report.service.model.PostModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;


public class HelperUtil {

    private final PostDao postDao;

    @Autowired
    public HelperUtil(PostDao postDao) {
        this.postDao = postDao;
    }

    public void insertDemoData() {
        // Check if there is existing data
        if (postDao.count() == 0) {
            // Insert demo data
            PostModal post1 = createPost("John Doe", "Full-time", "Exciting job opportunity", "3 years of experience",
                    new String[]{"Java", "Spring Boot"}, "100000");
            postDao.save(post1);

            PostModal post2 = createPost("Jane Smith", "Part-time", "Internship position", "1 year of experience",
                    new String[]{"Python", "Django"}, "999");
            postDao.save(post2);

            // Add more demo data as needed

            System.out.println("Demo data inserted successfully!");
        } else {
            System.out.println("Data already exists in the database.");
        }
    }

    private PostModal createPost(String profile, String type, String description, String experience,
                                 String[] technology, String salary) {
        PostModal post = new PostModal();
        post.setProfile(profile);
        post.setType(type);
        post.setDescription(description);
        post.setExperience(experience);
        post.setTechnology(technology);
        post.setSalary(salary);
        post.setCreatedAt(new Date());
        return post;
    }
}
