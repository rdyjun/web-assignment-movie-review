package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.email.EmailService;
import com.dongyang.moviewreviewweb.moviereviewer.log.Log;
import com.dongyang.moviewreviewweb.moviereviewer.log.LogDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.DeleteAccountService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.LoginService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.MemberService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.RegisterService;
import com.dongyang.moviewreviewweb.moviereviewer.movie.service.MovieService;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final LoginService loginService;
    private final RegisterService registerService;
    private final DeleteAccountService deleteAccountService;
    private final MovieService movieService;
    private final LogDAO logDAO;
    private final ReviewLikeService reviewLikeService;
    private final ReviewService reviewService;
    private final MemberService memberService;
    @PostMapping("/login-validate")
    public String login (Login loginDTO, HttpSession httpSession) throws SQLException, ClassNotFoundException, AccessDeniedException {
        loginService.login(loginDTO, httpSession);
        if (httpSession.getAttribute("memberId") == null) {
            Log log = new Log("로그인 실패", loginDTO.getId());
            logDAO.create(log);
            return "redirect:/login";
        }
        Log log = new Log("로그인", (String) httpSession.getAttribute("memberId"));
        logDAO.create(log);
        String prevURL = (String) httpSession.getAttribute("prevURL");
        if (prevURL == null || prevURL.equals("/login") || prevURL.equals("/register"))
            return "redirect:/";
        return "redirect:" + prevURL;
    }
    @PostMapping("/register-validate")
    public String register (Register register, HttpSession session) throws SQLException, ClassNotFoundException {
        if (session.getAttribute("verification") == null)
            throw new IllegalArgumentException("이메일 인증 정보가 존재하지 않습니다.");
        registerService.register(register);
        Log log = new Log("회원가입", register.getId());
        logDAO.create(log);
        session.invalidate();
        return "redirect:/";
    }
    @PostMapping("/delete-account")
    public String deleteAccount (HttpSession httpSession) throws SQLException, ClassNotFoundException {
        String memberId = (String) httpSession.getAttribute("memberId");
        deleteAccountService.deleteAccount(memberId);
        Log log = new Log("회원 탈퇴 (계정 삭제)", memberId);
        logDAO.create(log);
        httpSession.invalidate();
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout (HttpSession httpSession) {
        Log log = new Log("로그아웃", (String) httpSession.getAttribute("memberId"));
        logDAO.create(log);
        String prevURL = (String) httpSession.getAttribute("prevURL");
        httpSession.invalidate();
        if (prevURL == null || prevURL.equals("/login") || prevURL.equals("/register"))
            return "redirect:/";
        return "redirect:" + prevURL;
    }
    @ExceptionHandler({NoSuchElementException.class,
            IllegalArgumentException.class,
            AccessDeniedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage() + "\n 페이지를 뒤로 이동해주세요";
    }
    @RequestMapping("/mypage")
    public String getMovieInfo (HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId == null || memberId.equals(""))
            return "/";
        Member member = memberService.getMemberData(memberId);
        List<Review> reviewList = reviewService.getReviewByMemberId(memberId);
        Collections.reverse(reviewList);
        Map<Long, Integer> reviewLikeList = new HashMap<>();
        for (Review review : reviewList)
            reviewLikeList.put(review.getId(), reviewLikeService.getReviewLikeByReviewId(review.getId()));
        List<ReviewLike> myLikeList = reviewLikeService.getReviewLikeByMemberId(memberId);
        List<Long> myLikeListAtId = myLikeList.stream()
                .map(v -> v.getReviewId())
                .toList();
        List<String> reviewMovieName = reviewList.stream()
                .map(v -> {
                    try {
                        return movieService.getTargetMovie(v.getMovieId()).getTitle();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        model.addAttribute("member", member);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("likeCount", reviewLikeList);
        model.addAttribute("like", myLikeListAtId);
        model.addAttribute("reviewMovieTitle", reviewMovieName);
        return "/mypage";
    }
}
