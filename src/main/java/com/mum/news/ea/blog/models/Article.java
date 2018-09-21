package com.mum.news.ea.blog.models;

import javax.persistence.*;
import java.util.Date;

/**
 * https://medium.com/@joeclever/using-multiple-datasources-with-spring-boot-and-spring-data-6430b00c02e7
 * User: franc
 * Date: 21/09/2018
 * Time: 1:42
 */
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Lob
    private String content;
    private Date date;
    private User author;

    private String image;
    private Category category;


}
