package com.report.service.dao;

import com.report.service.model.PostModal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDao extends MongoRepository<PostModal, String> {
}
