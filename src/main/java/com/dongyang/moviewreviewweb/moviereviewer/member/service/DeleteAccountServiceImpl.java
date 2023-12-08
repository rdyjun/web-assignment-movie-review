package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReportRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewDAO;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewLikeRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImpl implements DeleteAccountService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReportRepository reportRepository;
    @Override
    public void deleteAccount(String memberId) {
        reportRepository.removeByReporterId(memberId);
        reviewLikeRepository.removeByMemberId(memberId);
        reviewRepository.removeByMemberId(memberId);
        memberRepository.removeById(memberId);
    }
}
