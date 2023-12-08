package com.dongyang.moviewreviewweb.moviereviewer.member.repository;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Register;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById (String id);
    Optional<Member> findByUserName(String name);
    boolean save(Register Member);
    Optional<Member> findByIdAndPw (String id, String pw);
    List<Member> findAll ();
    void removeById (String id);
    List<Member> findByBlackList (boolean status);
    void updateBlockByIdAndStatus (String id, boolean status);
    boolean findByIdAtStatus (String memberId);

    void updateMemberNameByMemberId(String memberId, String newName);
}
