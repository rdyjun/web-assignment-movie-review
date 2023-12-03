package com.dongyang.moviewreviewweb.moviereviewer.movie.controller;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieDTO;
import com.dongyang.moviewreviewweb.moviereviewer.movie.service.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
        String moviesJson = movieService.getTrendingMovies();
        List<MovieDTO> mdto = movieService.jsonConvertToMovie(moviesJson);
        model.addAttribute("movies", mdto);
        return "movies";
    }
}
