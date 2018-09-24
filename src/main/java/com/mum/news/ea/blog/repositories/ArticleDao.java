package com.mum.news.ea.blog.repositories;

/**
 * User: franc
 * Date: 21/09/2018
 * Time: 2:08
 */

import com.mum.news.ea.blog.models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ArticleDao extends CrudRepository<Article, Long> {

    public List<Article> findAllByCategory_IdOrderByPublicationDateAsc(long categoryId);

    public List<Article> findAllByCategory_IdOrderByPublicationDateDesc(long categoryId);

    public List<Article> findAllByOrderByPublicationDateAsc();

    public List<Article> findAllByOrderByPublicationDateDesc();
}
