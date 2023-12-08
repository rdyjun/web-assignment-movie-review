package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public List<MemberFace> getNoneBlackListMemberList() {
        List<Member> memberList = memberRepository.findByBlackList(false);
        List<MemberFace> memberFaceList = new ArrayList<>();
        for (Member member : memberList)
            memberFaceList.add(new MemberFace(member));
        return memberFaceList;
    }

    @Override
    public List<MemberFace> getBlackListMemberList() {
        List<Member> memberList = memberRepository.findByBlackList(true);
        List<MemberFace> memberFaceList = new ArrayList<>();
        for (Member member : memberList)
            memberFaceList.add(new MemberFace(member));
        return memberFaceList;
    }
    @Override
    public void reverseMemberStatus (String memberId) {
        boolean status = memberRepository.findByIdAtStatus(memberId);
        if (status)
            memberRepository.updateBlockByIdAndStatus(memberId, false);
        if (!status)
            memberRepository.updateBlockByIdAndStatus(memberId, true);
    }
    @Override
    public Member getMemberData(String memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty())
            throw new IllegalArgumentException("회원 정보가 존재하지 않습니다.");
        return member.get();
    }
    @Override
    public void modifyMemberName(String memberId, String newName) {
        memberRepository.updateMemberNameByMemberId(memberId, newName);
    }
    @Override
    public List<String> getMemberNameByReview(List<Review> reviewList) {
        List<String> memberNameList = new ArrayList<>();
        for (Review r : reviewList) {
            Optional<Member> m = memberRepository.findById(r.getAuthor());
            if (m.isEmpty())
                throw new IllegalArgumentException("존재하지 않는 회원입니다.");
            memberNameList.add(m.get().getName());
        }
        return memberNameList;
    }
}
