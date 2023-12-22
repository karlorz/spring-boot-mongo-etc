package com.report.service.service;

import com.report.service.dao.PostDao;
import com.report.service.dto.PostDTO;
import com.report.service.model.PostModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostDao postDao;

    @Override
    public List<PostModal> getAllPosts() {
        return postDao.findAll();
    }

    @Override
    public PostModal savePost(PostDTO postDTO) {
        PostModal postModal = new PostModal();

        postModal.setProfile(postDTO.getProfile());
        postModal.setType(postDTO.getType());
        postModal.setSalary(postDTO.getSalary());
        postModal.setExperience(postDTO.getExperience());
        postModal.setDescription(postDTO.getDescription());
        postModal.setTechnology(postDTO.getTechnology());

        return postDao.save(postModal);
    }
}
