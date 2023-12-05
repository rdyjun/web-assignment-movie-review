package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReportDAO;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl {
    private final ReportDAO reportDAO;
    private final ReviewDAO reviewDAO;
    public List<Report> getAllReport () {
        return reportDAO.findAll();
    }
    public List<String> getAllReportComment (List<Report> reportList) {
        List<String> contentList = new ArrayList<>();
        for (Report r : reportList) {
            Optional<Review> review = reviewDAO.findById(r.getReviewId());
            if (review.isEmpty())
                continue;
            String reviewComment = String.valueOf(review.get());
            contentList.add(reviewComment);
        }
        return contentList;
    }
}
