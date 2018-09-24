package com.mum.news.ea.blog.repositories;

import com.mum.news.ea.blog.models.Comment;

/**
 * User: franc
 * Date: 24/09/2018
 * Time: 5:42
 */




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentDao extends MongoRepository<Comment, String> {
}