package com.nash.ecommerce.entites;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "metaTitle")
    private String metaTitle;

    @Column(name = "slug")
    private String slug;

    @Column(name = "tagcol")
    private String tagcol;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setTagcol(String tagcol) {
        this.tagcol = tagcol;
    }

    public String getTagcol() {
        return tagcol;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id + '\'' +
                "title=" + title + '\'' +
                "metaTitle=" + metaTitle + '\'' +
                "slug=" + slug + '\'' +
                "tagcol=" + tagcol + '\'' +
                '}';
    }
}
