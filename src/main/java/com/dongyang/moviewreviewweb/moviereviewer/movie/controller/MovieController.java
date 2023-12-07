package com.dongyang.moviewreviewweb.moviereviewer.movie.controller;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.dongyang.moviewreviewweb.moviereviewer.movie.movieapi.MovieAPI;
import com.dongyang.moviewreviewweb.moviereviewer.movie.service.MovieServiceImpl;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLikeCount;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeServiceImpl;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    @PostMapping("/test")
    public void test () {
        System.out.println("");
    }
    @RequestMapping("/movies")
    public String getMovies (@RequestParam(value="sort", required = false) String sort, Model model) throws IOException {
        List<MovieList> mdto = movieService.getMovies(sort);
        model.addAttribute("movies", mdto);
        return "movies";
    }
    @RequestMapping("/mbti-movies")
    public String getMovies (Model model, String mbti) throws IOException {
        if (mbti == null)
            mbti = "INFP"; // 기본
        String moviesJson = movieService.getMovieByMBTI(mbti);
        List<MovieList> mdto = MovieAPI.jsonConvertToMovieList(moviesJson);
        model.addAttribute("movies", mdto);
        return "mbtiMovies";
    }
    @RequestMapping("/search")
    public String getSearchMovies (Model model, String searchValue) throws IOException {
        List<MovieList> movieList = movieService.getMovieByKeyword(searchValue);
        model.addAttribute("movies", movieList);
        model.addAttribute("searchValue", searchValue);
        return "search";
    }
    @RequestMapping("/movies/{id}")
    public String getMovieInfo (@PathVariable String id, HttpSession session, Model model) throws IOException {
        String memberId = (String) session.getAttribute("userId");
        Movie movie = movieService.getTargetMovie(id);
        List<Review> reviewList = reviewService.getReviewByMovie(id);
        Map<Long, Integer> likeCount = reviewLikeService.getLikeCount(id);
        Set<Long> reviewLikeList = reviewLikeService.getReviewLikeListByMovieIdAndMemberId(id, memberId);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("like", reviewLikeList);
        model.addAttribute("likeCount", likeCount);
        return "/movieReview";
    }
    @RequestMapping("/")
    public String root (Model model) throws JsonProcessingException {
        List<MovieList> movieList = movieService.getTopThreeMovie();
        List<Movie> movies = movieService.getMovieDataList(movieList);
        model.addAttribute("movies", movies);
        return "index";
    }
}
