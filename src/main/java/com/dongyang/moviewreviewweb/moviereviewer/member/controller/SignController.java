package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.LoginDTO;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.RegisterDTO;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.DeleteAccountService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.LoginService;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.RegisterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final LoginService loginService;
    private final RegisterService registerService;
    private final DeleteAccountService deleteAccountService;
    @PostMapping("/login-validate")
    public String login (LoginDTO loginDTO, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        loginService.login(loginDTO, httpSession);
        if (httpSession.getAttribute("userId") == null)
            return "redirect:/login";
        return "redirect:/";
    }
    @PostMapping("/register-validate")
    public String register (RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
        registerService.register(registerDTO);
        return "redirect:/";
    }
    @PostMapping("/delete-account")
    public String deleteAccount (HttpSession httpSession) throws SQLException, ClassNotFoundException {
        deleteAccountService.deleteAccount(httpSession);
        httpSession.invalidate();
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout (HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNoSuchElementException(NoSuchElementException ex) {
        return "An error occurred while processing your request: " + ex.getMessage();
    }
}
