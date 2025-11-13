package com.politecnicllevant.firstweb.service;

import com.politecnicllevant.firstweb.controller.MovieServlet;
import com.politecnicllevant.firstweb.model.Movie;
import com.politecnicllevant.firstweb.util.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class MovieService implements MovieServiceStaticImpl {
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies= em.createQuery("select m from Movie m",Movie.class).getResultList();

        return movies;
    }

    @Override
    public Movie findById(long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        Movie movie = em.find(Movie.class, id);
        em.persist(movie);
        em.close();
        return movie;

    }

    @Override
    public boolean addMovie(Movie newMovie) {
        List<Movie> movies = findAll();
        if(movies.contains(newMovie)){
            return false;
        }
        movies.add(newMovie);
        return true;
    }

    @Override
    public Movie deleteMovieById(long id) {
        List<Movie> movies = findAll();

        movies.remove(findById(id));
        return findById(id);
    }
}
