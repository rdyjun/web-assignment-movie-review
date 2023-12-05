package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final MemberDAO memberDAO;
    @Override
    public void register(Register register) {
        validateUserId(register.getId());
        validateUserName(register.getName());
        memberDAO.save(register);
    }
    public void validateUserName (String userName) {
        if (memberDAO.findByUserName(userName).isPresent())
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
    }
    public void validateUserId (String userId) {
        if (memberDAO.findById(userId).isPresent())
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");

    }
}
