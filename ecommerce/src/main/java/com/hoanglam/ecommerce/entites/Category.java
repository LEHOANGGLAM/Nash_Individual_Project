package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parentId")
    private Integer parentId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "metaTitle")
    private String metaTitle;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "content")
    private String content;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id + '\'' +
                "parentId=" + parentId + '\'' +
                "title=" + title + '\'' +
                "metaTitle=" + metaTitle + '\'' +
                "slug=" + slug + '\'' +
                "content=" + content + '\'' +
                '}';
    }
}
