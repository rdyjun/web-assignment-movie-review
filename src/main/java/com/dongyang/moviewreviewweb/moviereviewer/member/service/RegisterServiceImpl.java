package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MemberRepository memberRepository;

    @Override
    public void register(Register register) {
        validateMemberId(register.getId());
        validateMemberName(register.getName());
        Member member = new Member(register.getId(), register.getName(), register.getPw(), false);
        memberRepository.save(member);
    }

    @Override
    public void validateMemberName(String memberName) {
        if (memberRepository.findByName(memberName).isPresent())
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
    }

    @Override
    public void validateMemberId(String memberId) {
        if (memberRepository.findById(memberId).isPresent())
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
    }

}
