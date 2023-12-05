package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;

import java.util.List;

public interface MemberService {
    List<MemberFace> getNoneBlackListMemberList ();
    List<MemberFace> getBlackListMemberList ();
    void reverseMemberStatus (String memberId);
}
