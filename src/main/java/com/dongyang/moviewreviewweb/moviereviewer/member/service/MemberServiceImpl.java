package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
