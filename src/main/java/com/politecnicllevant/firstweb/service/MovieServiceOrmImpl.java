package com.politecnicllevant.firstweb.service;

import com.politecnicllevant.firstweb.DAO.MovieDAO;
import com.politecnicllevant.firstweb.DAO.MovieOrmDAO;
import com.politecnicllevant.firstweb.DTO.ShowMovieDTO;
import com.politecnicllevant.firstweb.model.Movie;
import com.politecnicllevant.firstweb.util.ConnectionManager;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class MovieServiceOrmImpl implements MovieService {
    MovieDAO dao = new MovieOrmDAO();

    @Override
    public List<MovieDTO> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies;

       //TODO

        return movies;
    }

    @Override
    public Movie findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();

        Movie movie = em.find(Movie.class,id);
        em.close();

        return movie;
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newMovie);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public Movie deleteMovieById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        Movie movieDelete;
        try {
            em.getTransaction().begin();
            movieDelete = em.find(Movie.class,id);
            if (movieDelete != null){
                em.remove(movieDelete);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar la película con ID " + id, e);

        } finally {
            em.close();
        }
        return movieDelete;
    }
}
