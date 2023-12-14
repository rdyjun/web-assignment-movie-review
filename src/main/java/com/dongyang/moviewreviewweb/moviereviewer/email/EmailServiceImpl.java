package com.dongyang.moviewreviewweb.moviereviewer.email;

import com.dongyang.moviewreviewweb.moviereviewer.member.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    //의존성 주입을 통해서 필요한 객체를 가져온다.
    private final JavaMailSender emailSender;
    private final RegisterService registerService;
    private final VerificationRepository verificationRepository;
    private String authNum; //랜덤 인증 코드

    //랜덤 인증 코드 생성
    @Override
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) (random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일 양식 작성
    @Override
    public SimpleMailMessage createEmailForm(String email) {
        createCode(); //인증 코드 생성
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email); //보낼 이메일 설정
        message.setSubject("[Talk Film] 가입 인증 번호 안내드립니다."); //제목 설정
        message.setText("Talk Film 가입 인증번호 입니다 : " + authNum);

        return message;
    }

    //실제 메일 전송
    @Override
    public String sendEmail(String toEmail) {
        registerService.validateMemberId(toEmail);
        SimpleMailMessage simpleMailMessage = createEmailForm(toEmail);
        //실제 메일 전송
        emailSender.send(simpleMailMessage);
        deleteAlreadyData(toEmail);
        Timestamp duration = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5));
        verificationRepository.save(new Verification(toEmail, authNum, duration));
        return authNum; //인증 코드 반환
    }
    @Override
    public void validateAuthorizationKey(String email, String key) {
        removeExpiredVerification();
        Optional<Verification> authorizationData = verificationRepository.findById(email);
        if (authorizationData.isEmpty())
            throw new IllegalArgumentException("인증정보가 존재하지 않습니다.");
        Verification authorization = authorizationData.get();
        if (!authorization.isCertified(key))
            throw new IllegalArgumentException("인증키가 올바르지 않습니다.");
        verificationRepository.deleteById(email);
    }
    @Override
    public void deleteVerificatedScheduler (String email) {
        verificationRepository.deleteById(email);
    }
    public void removeExpiredVerification () {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        verificationRepository.deleteAllBeforeTimestamp(now);
    }
    @Override
    public void deleteAlreadyData (String email) {
        Optional<Verification> v = verificationRepository.findById(email);
        if (v.isPresent())
            verificationRepository.deleteById(email);
    }
}