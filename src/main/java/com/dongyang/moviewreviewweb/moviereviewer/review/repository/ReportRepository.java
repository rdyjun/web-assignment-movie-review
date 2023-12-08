package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    List<Report> findAll();
    void save (String reporter, long reviewId, Timestamp reportTime);

    Optional<Report> findByReporterIdAndReviewId(String reporterId, long reviewId);

    void removeByReviewId(long reviewId);

    void removeByReporterId(String memberId);
}
