package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImpl implements DeleteAccountService {
    private final MemberRepository memberRepository;
    private final ReviewDAO reviewDAO;
    @Override
    public void deleteAccount(String memberId) {
        memberRepository.removeById(memberId);
        reviewDAO.removeByMemberId(memberId);
    }
}
