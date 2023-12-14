package com.dongyang.moviewreviewweb.moviereviewer.member.controller;

import com.dongyang.moviewreviewweb.moviereviewer.email.EmailService;
import com.dongyang.moviewreviewweb.moviereviewer.email.Issued;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailVerifiedController {
    private final EmailService emailService;
    @PostMapping("/register/mailConfirm")
    public ResponseEntity mailConfirm(@RequestBody Issued issued) throws MessagingException, UnsupportedEncodingException {
        emailService.sendEmail(issued.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/register/mailCertify")
    public ResponseEntity verfication (@RequestBody Issued issued) {
        emailService.validateAuthorizationKey(issued.getEmail(), issued.getKey());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/register/deleteCertify")
    public ResponseEntity removeCertify (@RequestBody Issued issued) {
        emailService.deleteVerificatedScheduler(issued.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
