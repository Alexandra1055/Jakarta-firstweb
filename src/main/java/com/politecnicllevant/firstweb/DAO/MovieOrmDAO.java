package com.politecnicllevant.firstweb.DAO;

import com.politecnicllevant.firstweb.model.Movie;
import com.politecnicllevant.firstweb.service.MovieService;
import com.politecnicllevant.firstweb.util.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovieOrmDAO implements MovieDAO
{
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();

        em.find();
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