package com.report.service.service;

import com.report.service.exception.ResourceNotFoundException;
import com.report.service.repository.PostRepository;
import com.report.service.documnent.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

//    @Autowired
//    PostRepository postRepository;
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getByProfile(String profile) {
        Optional<Post> postDb =postRepository.getByProfile(profile);
        if(postDb.isPresent()) {
            return postDb.get();
        }else {
            throw new ResourceNotFoundException("Record not found with profile : " + profile);
        }
    }
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

}
