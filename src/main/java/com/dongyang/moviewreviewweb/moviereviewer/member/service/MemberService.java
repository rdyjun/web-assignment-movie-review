package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;

import java.util.List;

public interface MemberService {
    List<MemberFace> getNoneBlackListMemberList ();
    List<MemberFace> getBlackListMemberList ();
    void reverseMemberStatus (String memberId);

    Member getMemberData(String memberId);

    void modifyMemberName(String memberId, String newName);

    List<String> getMemberNameByReview(List<Review> reviewList);
}
