package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.*;
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
    public List<ReportFace> getAllReportComment (List<Report> reportList) {
        List<ReportFace>contentList = new ArrayList<>();
        for (Report r : reportList) {
            Optional<Review> review = reviewDAO.findById(r.getReviewId());
            if (review.isEmpty())
                continue;

            Review rv = review.get();
            ReportFace rf = new ReportFace(rv.getAuthor(), rv.getComment());
            contentList.add(rf);
        }
        return contentList;
    }
}
