package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public void login (Login loginData, HttpSession httpSession) throws AccessDeniedException {
        Optional<Member> member = memberRepository.findByIdAndPw(loginData.getId(), loginData.getPw());
        if (member.isEmpty()) {
            throw new NoSuchElementException("회원 정보가 존재하지 않습니다.");
        }

        if (member.get().isStatus()) {
            throw new AccessDeniedException("접근이 거부된 계정입니다.");
        }

        httpSession.setAttribute("memberId", loginData.getId());
    }

}
