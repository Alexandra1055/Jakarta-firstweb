package com.politecnicllevant.firstweb.service;

import com.politecnicllevant.firstweb.model.Movie;
import com.politecnicllevant.firstweb.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieServiceJdbcImpl implements MovieServiceStaticImpl {

    @Override
    public List<Movie> findAll() {
        String sql = "SELECT id, title, description, year FROM movies";
        List<Movie> out = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                out.add(new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error JDBC al listar movies", e);
        }
        return out;
    }

    @Override
    public Movie findById(long id) {
        String sql = "SELECT id, title, description, year FROM movies WHERE id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("year")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error JDBC al obtener movie por id", e);
        }
        return null;
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        String sqlExists = "SELECT 1 FROM movies WHERE LOWER(title) = LOWER(?) AND year = ? LIMIT 1";
        String sqlInsert = "INSERT INTO movies(title, description, year) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(sqlExists)) {
                pst.setString(1, newMovie.getTitle());
                pst.setInt(2, newMovie.getYear());
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return false;
                    }
                }
            }

            try (PreparedStatement pst = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, newMovie.getTitle());
                pst.setString(2, newMovie.getDescription());
                pst.setInt(3, newMovie.getYear());

                int affected = pst.executeUpdate();
                if (affected == 0) return false;

                try (ResultSet keys = pst.getGeneratedKeys()) {
                    if (keys.next()) newMovie.setId(keys.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error JDBC al crear movie", e);
        }
    }


    @Override
    public Movie deleteMovieById(long id) {
        Movie prev = findById(id);
        if (prev == null) return null;

        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, id);
            pst.executeUpdate();
            return prev;
        } catch (SQLException e) {
            throw new RuntimeException("Error JDBC al borrar movie", e);
        }
    }
}
