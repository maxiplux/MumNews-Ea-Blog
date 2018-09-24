package com.mum.news.ea.blog.repositories;

import com.mum.news.ea.blog.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Long> {
}
