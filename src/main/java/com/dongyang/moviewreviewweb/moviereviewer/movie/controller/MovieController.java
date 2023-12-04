package com.dongyang.moviewreviewweb.moviereviewer.movie.controller;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieDTO;
import com.dongyang.moviewreviewweb.moviereviewer.movie.service.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;
    @PostMapping("/test")
    public void test () {
        System.out.println(movieService.getMovieByMBTI("N"));
    }
    @RequestMapping("/movies")
    public String getMovies (Model model) throws JsonProcessingException {
        String moviesJson = movieService.getMovies();
        List<MovieDTO> mdto = movieService.jsonConvertToMovie(moviesJson);
        model.addAttribute("movies", mdto);
        return "movies";
    }
    @RequestMapping("/mbti-movies")
    public String getMovies (Model model, String mbti) throws JsonProcessingException {
        if (mbti == null)
            mbti = "INFP";
        String moviesJson = movieService.getMovieByMBTI(mbti);
        List<MovieDTO> mdto = movieService.jsonConvertToMovie(moviesJson);
        model.addAttribute("movies", mdto);
        return "movies";
    }
}
