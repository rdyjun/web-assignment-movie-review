package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.*;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReportRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }
    @Override
    public List<ReportFace> getAllReportComment(List<Report> reportList) {
        List<ReportFace>contentList = new ArrayList<>();
        for (Report r : reportList) {
            Optional<Review> review = reviewRepository.findById(r.getReviewId());
            if (review.isEmpty())
                continue;

            Review rv = review.get();
            ReportFace rf = new ReportFace(rv.getAuthor(), rv.getComment());
            contentList.add(rf);
        }
        return contentList;
    }
    @Override
    public void reportReview(String reporterId, long reviewId) {
        validateReport(reporterId, reviewId);
        reportRepository.save(reporterId, reviewId, Timestamp.valueOf(LocalDateTime.now()));
    }
    @Override
    public void validateReport(String reportId, long reviewId) {
        if (reportRepository.findByReporterIdAndReviewId(reportId, reviewId).isPresent())
            throw new IllegalArgumentException("이미 신고하였습니다.");
    }
    @Override
    public void removeReport (long reviewId) {
        reportRepository.removeByReviewId(reviewId);
    }
}
