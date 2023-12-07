package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;

import java.util.List;

public interface ReportRepository {
    List<Report> findAll();
}
