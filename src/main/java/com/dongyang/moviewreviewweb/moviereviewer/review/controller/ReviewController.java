package com.dongyang.moviewreviewweb.moviereviewer.review.controller;

import com.dongyang.moviewreviewweb.moviereviewer.movieapi.MovieListAPI;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewPassingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewPassingService reviewPassingService;
    private final MovieListAPI movieListAPI;
    @PostMapping("/rank")
    public List<String> getRank () {
        return reviewPassingService.getRank();
    }
}
