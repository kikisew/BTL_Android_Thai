package com.example.btl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("articles")
    @Expose
    private List<String> articles;

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String img) {
        this.name = name;
        this.img = img;
        this.slug = slug;
        this.articles = articles;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }
}
