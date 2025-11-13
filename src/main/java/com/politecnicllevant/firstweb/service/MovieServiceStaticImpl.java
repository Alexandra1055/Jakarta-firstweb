package com.politecnicllevant.firstweb.service;

import com.politecnicllevant.firstweb.model.Movie;

import java.util.List;

public interface MovieServiceStaticImpl {
    public static List<Movie> movies = List.of();

    public List<Movie> findAll();
    public Movie findById(long id);
    public boolean addMovie(Movie newMovie);
    public Movie deleteMovieById(long id);

}
