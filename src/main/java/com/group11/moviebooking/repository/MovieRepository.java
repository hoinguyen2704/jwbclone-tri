package com.group11.moviebooking.repository;

import com.group11.moviebooking.entity.MovieEntity;
import com.group11.moviebooking.model.MovieDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MovieRepository {

    List<MovieEntity> getMovies(MovieEntity similar, int at, byte total);

    List<MovieEntity> getMoviesByTitle(String movie_title);

    String getMovies(int movie_id);

    MovieEntity getMoviesById(int movie_id);

    List<MovieEntity> getAllMovies();

    List<MovieEntity> getAllMovies(int page);

    ArrayList<MovieEntity> getLatestMovies();

    ArrayList<MovieEntity> getLatestMovies(int page);

    ArrayList<MovieEntity> getTopMovieByRating();

    ArrayList<MovieEntity> getTopMovieByRating(int page);

    ArrayList<MovieEntity> getMoviesForAdults();

    ArrayList<MovieEntity> getMoviesForAdults(int page);

    ArrayList<MovieEntity> getMoviesForKids();

    ArrayList<MovieEntity> getMoviesForKids(int page);

    ArrayList<MovieDTO> getTopSellingMovies();

    HashMap<Object, Object> getTicketsSoldAndRevenue();

    boolean addMovie(MovieDTO entity);

    boolean updateMovie(MovieDTO entity);

    boolean deleteMovie(int id);
}
