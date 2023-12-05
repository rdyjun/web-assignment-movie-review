package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final MemberRepository memberRepository;
    @Override
    public void login (Login loginData, HttpSession httpSession) throws AccessDeniedException {
        Optional<Member> member = memberRepository.findByIdAndPw(loginData.getId(), loginData.getPw());
        if (member.isEmpty())
            throw new NoSuchElementException("회원 정보가 존재하지 않습니다.");
        if (member.get().getStatus())
            throw new AccessDeniedException("접근이 거부된 계정입니다.");
        httpSession.setAttribute("userId", loginData.getId());
    }
}