package com.dongyang.moviewreviewweb.moviereviewer.email;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    //랜덤 인증 코드 생성
    void createCode();

    //메일 양식 작성
    SimpleMailMessage createEmailForm(String email) throws MessagingException;

    //실제 메일 전송
    String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException;

    void validateAuthorizationKey(String email, String key);

    void deleteVerificatedScheduler(String email);
}
