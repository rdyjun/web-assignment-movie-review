package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.log.Log;
import com.dongyang.moviewreviewweb.moviereviewer.log.LogDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.DeleteAccountService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.LoginService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.RegisterService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final LoginService loginService;
    private final RegisterService registerService;
    private final DeleteAccountService deleteAccountService;
    private final LogDAO logDAO;
    @PostMapping("/login-validate")
    public String login (Login loginDTO, HttpSession httpSession) throws SQLException, ClassNotFoundException, AccessDeniedException {
        loginService.login(loginDTO, httpSession);
        if (httpSession.getAttribute("userId") == null) {
            Log log = new Log("로그인 실패", loginDTO.getId());
            logDAO.create(log);
            return "redirect:/login";
        }
        Log log = new Log("로그인", (String) httpSession.getAttribute("userId"));
        logDAO.create(log);
        return "redirect:" + httpSession.getAttribute("prevURL");
    }
    @PostMapping("/register-validate")
    public String register (Register register) throws SQLException, ClassNotFoundException {
        registerService.register(register);
        Log log = new Log("회원가입", register.getId());
        logDAO.create(log);
        return "redirect:/";
    }
    @PostMapping("/delete-account")
    public String deleteAccount (HttpSession httpSession) throws SQLException, ClassNotFoundException {
        deleteAccountService.deleteAccount(httpSession);
        Log log = new Log("회원 탈퇴 (계정 삭제)", (String) httpSession.getAttribute("userId"));
        logDAO.create(log);
        httpSession.invalidate();
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout (HttpSession httpSession) {
        Log log = new Log("로그아웃", (String) httpSession.getAttribute("userId"));
        logDAO.create(log);
        httpSession.invalidate();
        return "redirect:/";
    }
    @ExceptionHandler({NoSuchElementException.class,
            IllegalArgumentException.class,
            AccessDeniedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage() + "\n 페이지를 뒤로 이동해주세요";
    }
}
