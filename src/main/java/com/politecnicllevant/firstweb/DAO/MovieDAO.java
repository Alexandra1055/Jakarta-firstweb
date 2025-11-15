package com.politecnicllevant.firstweb.DAO;

import com.politecnicllevant.firstweb.model.Movie;

import java.util.List;

public interface MovieDAO {
    public static List<Movie> movies = List.of();

    public List<Movie> findAll();
    public Movie findById(long id);

    Movie findById(Long id);

    public boolean addMovie(Movie newMovie);
    public Movie deleteMovieById(long id);

    Movie deleteMovieById(Long id);
}
