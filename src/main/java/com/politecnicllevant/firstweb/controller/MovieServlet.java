package com.politecnicllevant.firstweb.controller;

import com.politecnicllevant.firstweb.model.Movie;
import com.politecnicllevant.firstweb.service.MovieServiceJdbcImpl;
import com.politecnicllevant.firstweb.service.MovieService;
import com.politecnicllevant.firstweb.service.MovieServiceStaticImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//localhost:8080/firstweb/movie

//@WebServlet(name = "movieServlet", value = "/firstweb/movie")
public class MovieServlet extends HttpServlet {
    private MovieServiceStaticImpl movieService = new MovieServiceStaticImpl();
    public static List<Movie> movieList;


   /*
    MovieService movieService = new MovieService();
   */

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //Tenemos que hacer, si no hay ningun parametro que nos devuelva la lista de movies
    // si hay parametros que enseñe la movie con eso ej:http://localhost:8080/firstweb/movies id=3
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*

        movieList = movieService.findAll();
        String idpelicula = req.getParameter("id");

        long idCast = Long.parseLong(idpelicula);

        movieList = movieList.stream().
                filter(movie -> movie.getId() == idCast).
                toList();

* */
        //List<Movie> movieList = new ArrayList<>();
        //load driver


       /* try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            Connection conn = jdbcConnector.connect();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM movies");

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Long movieId = result.getLong("id");
                String movieTitle = result.getString("title");
                String movieDes = result.getString("description");
                int movieYear = result.getInt("year");

                Movie movie = new Movie(movieId, movieTitle, movieDes, movieYear);
                movieList.add(movie);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            showAllMovies(req, resp);
        } else {
            showMovie(req, resp);
        }
        String err = req.getParameter("error");
        if ("duplicated".equalsIgnoreCase(err)) {
            req.setAttribute("error", "Esa película (título + año) ya existe.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String override = req.getParameter("_method");
        if (override != null && "DELETE".equalsIgnoreCase(override)) {
            handleDelete(req, resp);
            return;
        }
        handleCreate(req, resp);
    }

    private void showAllMovies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Movie> movies = movieService.findAll();
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("movie.jsp").forward(req, resp);
    }

    private void showMovie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Movie> list;
        try {
            long id = Long.parseLong(req.getParameter("id"));
            Movie m = movieService.findById(id);
            list = (m == null) ? List.of() : List.of(m);
        } catch (NumberFormatException e) {
            list = List.of();
        }
        req.setAttribute("movies", list);
        req.getRequestDispatcher("movie.jsp").forward(req, resp);
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String yearStr = req.getParameter("year");

        int year = 0;
        try {
            if (yearStr != null)
                year = Integer.parseInt(yearStr.trim());
        } catch (Exception ignored) {

        }

        boolean ok = true;
        if (title != null && !title.isBlank()) {
            Movie m = new Movie(0L, title.trim(), (description == null ? "" : description.trim()), year);
            ok = movieService.addMovie(m);
        }

        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/movies");
        } else {
            resp.sendRedirect(req.getContextPath() + "/movies?error=duplicated");
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String idStr = req.getParameter("id");
        try {
            long id = Long.parseLong(idStr);
            movieService.deleteMovieById(id);
        } catch (Exception ignored) {}
        resp.sendRedirect(req.getContextPath() + "/movies");
    }
}