package com.dongyang.moviewreviewweb.moviereviewer.member.repository;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByIdAndPw(String id, String pw);

    List<Member> findByStatus(boolean status);

    Optional<Object> findByName(String name);
}
