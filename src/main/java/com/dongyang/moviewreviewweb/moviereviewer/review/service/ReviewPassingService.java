package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import java.util.List;

public interface ReviewPassingService {
    void getReviewData (Long movieCode);
    List<String> getRank ();
}
