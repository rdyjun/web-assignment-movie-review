package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.log.Log;
import com.dongyang.moviewreviewweb.moviereviewer.log.LogDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.MemberService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ReviewLikeService reviewLikeService;
    private final ReviewService reviewService;
    private final LogDAO logDAO;
    @RequestMapping("/modify-name")
    public String modifyName (HttpSession session, String newName) {
        String memberId = (String) session.getAttribute("memberId");
        memberService.modifyMemberName(memberId, newName);
        Log log = new Log("회원 이름 변경 : " + newName, memberId);
        logDAO.create(log);
        return "redirect:/mypage";
    }
}
