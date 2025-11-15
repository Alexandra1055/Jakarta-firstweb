package com.politecnicllevant.firstweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name= "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String title;
    protected String description;
    protected int year;

    @Transient //asi ya no lo tiene en cuenta en el constructor
    private float rate;

    @OneToMany
    List<Comment> comments;

    public Movie(long id, String title, String description, int year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
    }

    public Movie() {

    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
