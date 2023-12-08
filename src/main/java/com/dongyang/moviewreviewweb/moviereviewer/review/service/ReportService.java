package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReportFace;

import java.util.List;

public interface ReportService {
    List<Report> getAllReport();

    List<ReportFace> getAllReportComment(List<Report> reportList);

    void reportReview(String reporterId, long reviewId);

    void validateReport(String reportId, long reviewId);

    void removeReport(long reviewId);
}
