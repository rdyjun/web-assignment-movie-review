package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.email.EmailService;
import com.dongyang.moviewreviewweb.moviereviewer.email.Issued;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class EmailVerifiedController {
    private final EmailService emailService;
    @PostMapping("/register/mailConfirm")
    public ResponseEntity mailConfirm(@RequestBody Issued issued, HttpSession session) throws MessagingException, UnsupportedEncodingException {
        String key = emailService.sendEmail(issued.getEmail());
        session.setAttribute("key", key);
        Timestamp duration = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5));
        session.setAttribute("keyDuration", duration);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/register/mailCertify")
    public ResponseEntity verfication (@RequestBody Issued issued, HttpSession session) {
        emailService.removeExpiredVerification(session);
        emailService.validateAuthorizationKey(issued.getEmail(), issued.getKey(), session);
        session.setAttribute("verification", issued.getKey());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
