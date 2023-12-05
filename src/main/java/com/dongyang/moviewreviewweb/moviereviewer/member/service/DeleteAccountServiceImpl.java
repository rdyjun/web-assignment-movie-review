package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountServiceImpl implements DeleteAccountService {
    private final MemberDAO memberDAO;
    private final ReviewDAO reviewDAO;
    @Override
    public void deleteAccount(HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");
        memberDAO.removeById(userId);
        reviewDAO.removeByMemberId(userId);
    }
}
