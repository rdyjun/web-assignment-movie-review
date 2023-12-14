package com.dongyang.moviewreviewweb.moviereviewer.email;

import jakarta.servlet.http.HttpSession;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    //랜덤 인증 코드 생성
    void createCode();

    //메일 양식 작성
    SimpleMailMessage createEmailForm(String email);

    //실제 메일 전송
    String sendEmail(String toEmail);

    void validateAuthorizationKey(String email, String key, HttpSession session);

    void removeExpiredVerification(HttpSession session);
}
