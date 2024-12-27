package com.group11.moviebooking.controller;

import com.group11.moviebooking.convert.MappingDTOtoJSON;
import com.group11.moviebooking.model.MovieDTO;
import com.group11.moviebooking.service.MovieService;
import com.group11.moviebooking.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {

    private final MovieService movieService;
    private final RevenueService revenueService;
    private MappingDTOtoJSON map;

    @Autowired
    public HomeController(MovieService movieService, RevenueService revenueService) {
        this.movieService = movieService;
        this.revenueService = revenueService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getIndexPage(@RequestParam(value = "page_popular") Optional<String> pageOption, Model model) {
        int page = 1;
        try {
            if (pageOption.isPresent()) {
                page = Integer.parseInt(pageOption.get());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ArrayList<MovieDTO> movies = this.movieService.getallMoviesLimit(4);
        ArrayList<MovieDTO> moviesRandom = this.movieService.getallMoviesLimit(4);
        ArrayList<MovieDTO> moviesMovieByRating = this.movieService.getTopMovieByRating(page);
        ArrayList<MovieDTO> moviesMovieByLatestMovies = this.movieService.getLatestMovies();
        model.addAttribute("moviesBannerSlide", movies);
        model.addAttribute("moviesMovieByRating", moviesMovieByRating);
        model.addAttribute("moviesMovieByLatestMovies", moviesMovieByLatestMovies);
        model.addAttribute("moviesRandom", moviesRandom);
        System.out.println(movies);
        return "index";
    }
}

