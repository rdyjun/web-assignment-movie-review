package com.dongyang.moviewreviewweb.moviereviewer.movie.service;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    List<MovieList> getMovies(String sortBy) throws IOException;

    String getMovieByMBTI(String mbti);

    List<MovieList> getMovieByKeyword(String keyword) throws IOException;

    String getMovieTypeByMBTI(String mbti);

    Movie getTargetMovie(String movieId) throws JsonProcessingException;

    List<MovieList> getTopThreeMovie() throws JsonProcessingException;

    List<Movie> getMovieDataList(List<MovieList> movieLists) throws JsonProcessingException;
}
