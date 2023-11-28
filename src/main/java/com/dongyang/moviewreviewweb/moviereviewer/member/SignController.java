package com.dongyang.moviewreviewweb.moviereviewer.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final LoginService loginService;
    @PostMapping("/login")
    public String login (@RequestBody LoginDTO loginDTO, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        loginService.login(loginDTO, httpSession);
        return "redirect:/";
    }
}
