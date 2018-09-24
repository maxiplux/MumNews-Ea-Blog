package com.mum.news.ea.blog.repositories;

import com.mum.news.ea.blog.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
