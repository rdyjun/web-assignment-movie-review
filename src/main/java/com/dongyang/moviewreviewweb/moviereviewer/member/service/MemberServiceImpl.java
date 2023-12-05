package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;
import com.dongyang.moviewreviewweb.moviereviewer.member.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    @Override
    public List<MemberFace> getNoneBlackListMemberList() {
        List<Member> memberList = memberDAO.findByBlackList(false);
        List<MemberFace> memberFaceList = new ArrayList<>();
        for (Member member : memberList)
            memberFaceList.add(new MemberFace(member));
        return memberFaceList;
    }

    @Override
    public List<MemberFace> getBlackListMemberList() {
        List<Member> memberList = memberDAO.findByBlackList(true);
        List<MemberFace> memberFaceList = new ArrayList<>();
        for (Member member : memberList)
            memberFaceList.add(new MemberFace(member));
        return memberFaceList;
    }
    @Override
    public void reverseMemberStatus (String memberId) {
        boolean status = memberDAO.findByIdAtStatus(memberId);
        if (status)
            memberDAO.updateBlockByIdAndStatus(memberId, false);
        if (!status)
            memberDAO.updateBlockByIdAndStatus(memberId, true);
    }
}
