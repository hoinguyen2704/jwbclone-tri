package com.group11.moviebooking.service;


import com.group11.moviebooking.entity.MovieEntity;
import com.group11.moviebooking.model.MovieDTO;
import com.group11.moviebooking.repository.MovieRepositoryImpl;
import com.group11.moviebooking.util.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepositoryImpl movieRepositoryImpl;

    public MovieService(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    public List<MovieDTO> getMovies(MovieEntity similar, int at, byte total) {
        List<MovieEntity> movieEntities = movieRepositoryImpl.getMovies(similar, at, total);
        List<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getMovies(String movie_title) {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getMoviesByTitle(movie_title);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();
        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getallMovies() {
        ArrayList<MovieEntity> movieEntities = (ArrayList<MovieEntity>) movieRepositoryImpl.getAllMovies();
        ArrayList<MovieDTO> movieDTOs = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getallMovies(int page) {
        ArrayList<MovieEntity> movieEntities = (ArrayList<MovieEntity>) movieRepositoryImpl.getAllMovies(page);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getallMoviesLimit(int limit) {
        ArrayList<MovieEntity> movieEntities = (ArrayList<MovieEntity>) movieRepositoryImpl.getAllMoviesLimit(limit);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getLatestMovies() {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getLatestMovies();
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getLatestMovies(int page) {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getLatestMovies(page);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getTopMovieByRating() {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getTopMovieByRating();
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getTopMovieByRating(int page) {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getTopMovieByRating(page);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getMoviesForAdults() {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getMoviesForAdults();
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }    public ArrayList<MovieDTO> getMoviesForAdults(int page) {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getMoviesForAdults(page);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getMoviesForKids() {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getMoviesForKids();
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getMoviesForKids(int page) {
        ArrayList<MovieEntity> movieEntities = movieRepositoryImpl.getMoviesForKids(page);
        ArrayList<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();

        for (MovieEntity movieEntity : movieEntities) {
            MovieDTO movieDTO = MovieMapper.mapEntityToDTO(movieEntity);
            movieDTOs.add(movieDTO);
        }
        return movieDTOs;
    }

    public ArrayList<MovieDTO> getTopSellingMovies() {
        return this.movieRepositoryImpl.getTopSellingMovies();
    }

    public HashMap<Object, Object> getTicketsSoldAndRevenue() {

        HashMap<Object, Object> resultMap = movieRepositoryImpl.getTicketsSoldAndRevenue();

        return resultMap;
    }

    public String getMovie(int movie_id) {
        return movieRepositoryImpl.getMovies(movie_id);
    }

    public MovieDTO getMovieByMovieId(int movie_id) {
        return MovieMapper.mapEntityToDTO(movieRepositoryImpl.getMoviesById(movie_id));
    }

    public boolean add(MovieDTO movie) {
        return movieRepositoryImpl.addMovie(movie);
    }

    public boolean update(MovieDTO movie) {
        return movieRepositoryImpl.updateMovie(movie);
    }

    public boolean delete(int movie_id) {
        return movieRepositoryImpl.deleteMovie(movie_id);
    }

}





